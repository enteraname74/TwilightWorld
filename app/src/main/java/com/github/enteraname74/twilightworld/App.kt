package com.github.enteraname74.twilightworld

import android.app.Application
import com.github.enteraname74.remote_data_source.remoteDataSourceModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(remoteDataSourceModule)
        }
    }
}