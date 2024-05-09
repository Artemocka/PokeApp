package com.example.domain.repository

import com.example.domain.models.pokemondata.PokemonData


interface GetPokemonDataRepo {
    suspend fun get(id:Int):Result<PokemonData>
}