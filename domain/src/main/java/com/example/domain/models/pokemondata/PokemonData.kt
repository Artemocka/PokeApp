package com.example.domain.models.pokemondata

import com.google.gson.annotations.SerializedName

data class PokemonData(
    @SerializedName("base_experience")
    val baseExperience: Int,
    val height: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val sprites: Sprites,
    val weight: Int,
    val stats: List<Stats>,
)