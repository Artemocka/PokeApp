package com.example.domain.usecase

import com.example.domain.models.pokemondata.PokemonData
import com.example.domain.repository.GetPokemonDataRepo


class GetPokemonDataUseCase (
    val repository: GetPokemonDataRepo
) {
    suspend fun execute(id:Int):Result<PokemonData>{
        return  repository.get(id)
    }
}