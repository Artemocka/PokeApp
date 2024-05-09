package com.dracul.pokeapp.di

import com.example.data.repository.GetPokemonDataImpl
import com.example.data.repository.GetPokemonsImpl
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val dataModule = module {

    single<com.example.data.api.PokeApi> {
        Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(com.example.data.api.PokeApi::class.java)
    }

    single<com.example.domain.repository.GetPokemonsRepo>{
        GetPokemonsImpl(get())
    }

    single<com.example.domain.repository.GetPokemonDataRepo>{
        GetPokemonDataImpl(get())
    }

}