package com.burhan.mercari.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.burhan.mercari.database.getDatabase
import com.burhan.mercari.repository.ProductsRepository

/**
 * Developed by tcbaras on 2019-06-09.
 */
class RefreshDataWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {

        return try {
            val database = getDatabase(applicationContext)
            val repository = ProductsRepository(database)
            repository.downloadAllProducts()
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

    companion object {
        const val WORK_NAME = "DataRefreshWorker"
    }
}