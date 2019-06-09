package com.burhan.mercari

import android.app.Application
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary

/**
 * Developed by tcbaras on 2019-06-09.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG){
            LeakCanary.install(this)
            Stetho.initializeWithDefaults(this)
        }
    }
}