package com.example.domain.repository

import com.example.domain.models.Page
import com.example.domain.models.Result


interface GetPokemonsRepo {
    suspend fun get(page: Page):Pair<List<Result>?,String?>
}