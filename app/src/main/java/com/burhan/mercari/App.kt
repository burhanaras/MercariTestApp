package com.burhan.mercari

import android.app.Application
import android.os.Build
import androidx.work.*
import com.burhan.mercari.database.getDatabase
import com.burhan.mercari.repository.ProductsRepository
import com.burhan.mercari.work.RefreshDataWorker
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

/**
 * Developed by tcbaras on 2019-06-09.
 */
class App : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)
    private val networkScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            LeakCanary.install(this)
            Stetho.initializeWithDefaults(this)
        }


        networkScope.launch {
            ProductsRepository(getDatabase(this@App)).downloadCategoryUrls()
        }
        applicationScope.launch { scheduleDataRefreshWork() }

    }

    private fun scheduleDataRefreshWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setRequiresDeviceIdle(true)
                }
            }.build()

        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            RefreshDataWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )

    }
}