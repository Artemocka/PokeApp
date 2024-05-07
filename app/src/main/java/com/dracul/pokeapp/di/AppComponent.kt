package com.dracul.techtask.di

import com.dracul.pokeapp.di.AppModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder{
        fun build():AppComponent
    }
}