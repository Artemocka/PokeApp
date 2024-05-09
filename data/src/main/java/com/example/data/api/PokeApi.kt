package com.example.data.api

import com.example.domain.models.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface PokeApi {

    @GET("pokemon?")
    suspend fun getPage(@Query("offset") offset:Int, @Query("limit") limit:Int): Response

}