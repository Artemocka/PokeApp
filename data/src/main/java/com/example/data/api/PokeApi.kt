package com.example.data.api

import com.example.domain.models.Response
import com.example.domain.models.pokemondata.PokemonData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PokeApi {

    @GET("pokemon?")
    suspend fun getPage(@Query("offset") offset:Int, @Query("limit") limit:Int): Response
    @GET("pokemon/{id}")
    suspend fun getPokemonData(@Path("id") id: Int): PokemonData


}