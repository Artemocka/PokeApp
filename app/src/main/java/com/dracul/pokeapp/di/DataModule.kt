package com.dracul.pokeapp.di

import com.dracul.pokeapp.data.api.PokeApi
import com.dracul.pokeapp.data.repository.GetPokemonsImpl
import com.dracul.pokeapp.domain.repository.GetPokemonsRepo
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single<PokeApi> {
        Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApi::class.java)
    }

    single<GetPokemonsRepo>{
        GetPokemonsImpl(get())
    }

}