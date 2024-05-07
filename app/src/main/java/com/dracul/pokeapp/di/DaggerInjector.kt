package com.dracul.pokeapp.di

import com.dracul.techtask.di.AppComponent
import com.dracul.techtask.di.DaggerAppComponent

object DaggerInjector {
    lateinit var appComponent: AppComponent
        private set

    fun init():Boolean{
        return !DaggerInjector::appComponent.isInitialized.runElse {
            appComponent = DaggerAppComponent.builder().build()
        }
    }
}
inline fun Boolean.runElse(block: () -> Unit): Boolean = !(!this).runIf(block)
inline fun Boolean.runIf(block: () -> Unit): Boolean {
    if (this) block()
    return this
}