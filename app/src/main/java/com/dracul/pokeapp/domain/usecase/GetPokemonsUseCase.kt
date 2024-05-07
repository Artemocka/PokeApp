package com.dracul.pokeapp.domain.usecase

import com.dracul.pokeapp.domain.models.Result
import com.dracul.pokeapp.domain.repository.GetPokemonsRepo
import com.dracul.pokeapp.utills.Page
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(
    val repository: GetPokemonsRepo
) {
    suspend fun execute(page: Page):Pair<List<Result>?,String?>{
        return  repository.get(page)
    }
}