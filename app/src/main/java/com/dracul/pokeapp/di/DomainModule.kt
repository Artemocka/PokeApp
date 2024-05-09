package com.dracul.pokeapp.di


import com.example.domain.usecase.GetPokemonsUseCase
import org.koin.dsl.module


val domainModule = module {
    single<com.example.domain.usecase.GetPokemonsUseCase> {
        com.example.domain.usecase.GetPokemonsUseCase(get())
    }

}
