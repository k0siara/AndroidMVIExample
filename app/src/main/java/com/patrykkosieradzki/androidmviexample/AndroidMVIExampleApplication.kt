package com.patrykkosieradzki.androidmviexample

import android.app.Application
import com.google.firebase.FirebaseApp
import com.patrykkosieradzki.androidmviexample.di.appModule
import com.patrykkosieradzki.androidmviexample.storage.di.storageModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class AndroidMVIExampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@AndroidMVIExampleApplication)
            modules(appModule, storageModule)
        }
    }
}
