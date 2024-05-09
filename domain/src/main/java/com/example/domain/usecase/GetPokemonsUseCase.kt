package com.example.domain.usecase

import com.example.domain.models.Page
import com.example.domain.models.Result
import com.example.domain.repository.GetPokemonsRepo



class GetPokemonsUseCase (
    val repository: GetPokemonsRepo
) {
    suspend fun execute(page: Page):kotlin.Result<List<Result>>{
        return  repository.get(page)
    }
}