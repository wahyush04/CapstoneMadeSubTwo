package com.wahyush04.capstonemadesubone

import android.app.Application
import com.wahyush04.capstonemadesubone.di.useCaseModule
import com.wahyush04.capstonemadesubone.di.viewModelModule
import com.wahyush04.core.di.databaseModule
import com.wahyush04.core.di.networkModule
import com.wahyush04.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}