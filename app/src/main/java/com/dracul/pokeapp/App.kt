package com.dracul.pokeapp

import android.app.Application
import com.dracul.pokeapp.di.DaggerInjector
import com.google.android.material.color.DynamicColors

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        DaggerInjector.init()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}