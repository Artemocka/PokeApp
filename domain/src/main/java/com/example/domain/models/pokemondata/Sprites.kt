package com.example.domain.models.pokemondata

import com.google.gson.annotations.SerializedName

data class Sprites(
    @SerializedName("front_default") val frontDefault: String,
    @SerializedName("front_female") val frontFemale: String?,
    @SerializedName("front_shiny") val frontShiny: String,
    @SerializedName("front_shiny_female") val frontShinyFemale: String?,
    @SerializedName("back_default") val backDefault: String,
    @SerializedName("back_female") val backFemale: String?,
    @SerializedName("back_shiny") val backShiny: String,
    @SerializedName("back_shiny_female") val backShinyFemale: String?,
) {
    fun toStringList(): List<String> {
        val list = mutableListOf<String>()
        list.add(frontDefault)
        frontFemale?.let {
            list.add(it)
        }
        list.add(frontShiny)
        frontShinyFemale?.let {
            list.add(it)
        }
        list.add(backDefault)
        backFemale?.let {
            list.add(it)
        }
        list.add(backShiny)
        backShinyFemale?.let {
            list.add(it)
        }
        return list
    }
}