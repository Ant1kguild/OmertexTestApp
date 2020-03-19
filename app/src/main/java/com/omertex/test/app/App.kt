package com.omertex.test.app

import android.app.Application
import com.omertex.test.app.di.apiModule
import com.omertex.test.app.di.presentationModule
import com.omertex.test.app.di.dataModule
import com.omertex.test.app.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    apiModule,
                    dataModule,
                    domainModule,
                    presentationModule
                )
            )
        }
    }
}