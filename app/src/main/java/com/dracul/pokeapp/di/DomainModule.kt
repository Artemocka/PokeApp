package com.dracul.pokeapp.di


import com.dracul.pokeapp.domain.usecase.GetPokemonsUseCase
import org.koin.dsl.module


val domainModule = module {
    single<GetPokemonsUseCase> {
        GetPokemonsUseCase(get())
    }

}
