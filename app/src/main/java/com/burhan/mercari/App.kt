package com.burhan.mercari

import android.app.Application
import com.facebook.stetho.Stetho

/**
 * Developed by tcbaras on 2019-06-09.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}