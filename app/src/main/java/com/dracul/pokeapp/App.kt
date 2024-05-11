package com.dracul.pokeapp

import android.app.Application
import com.dracul.pokeapp.di.appModule
import com.dracul.pokeapp.di.dataModule
import com.dracul.pokeapp.di.domainModule
import com.google.android.material.color.DynamicColors
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
        startKoin {
            modules(listOf(appModule, dataModule, domainModule))
            androidContext(this@App)
        }
    }
}