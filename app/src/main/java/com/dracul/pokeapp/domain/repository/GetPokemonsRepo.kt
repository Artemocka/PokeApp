package com.dracul.pokeapp.domain.repository

import com.dracul.pokeapp.domain.models.Result
import com.dracul.pokeapp.utills.Page

interface GetPokemonsRepo {
    suspend fun get(page: Page):Pair<List<Result>?,String?>
}