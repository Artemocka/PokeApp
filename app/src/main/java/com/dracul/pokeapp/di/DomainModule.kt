package com.dracul.pokeapp.di


import com.example.domain.usecase.GetPokemonDataUseCase
import com.example.domain.usecase.GetPokemonsUseCase
import org.koin.dsl.module


val domainModule = module {
    single<GetPokemonsUseCase> {
        GetPokemonsUseCase(get())
    }

    single<GetPokemonDataUseCase> {
        GetPokemonDataUseCase(get())
    }
}
