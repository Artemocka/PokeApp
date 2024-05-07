package com.dracul.pokeapp.di

import com.dracul.pokeapp.di.components.main.MainDependencies
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent: MainDependencies {

    @Component.Builder
    interface Builder{
        fun build(): AppComponent
    }
}