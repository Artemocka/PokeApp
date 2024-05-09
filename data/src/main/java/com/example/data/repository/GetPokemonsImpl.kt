package com.example.data.repository

import com.example.data.api.PokeApi
import com.example.domain.models.Page
import com.example.domain.models.Result
import com.example.domain.repository.GetPokemonsRepo
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class GetPokemonsImpl(
    val api: PokeApi
) : GetPokemonsRepo {
    override suspend fun get(page: Page):kotlin.Result<List<Result>>{
        return try {
            kotlin.Result.success(api.getPage(page.getOffset(), page.getLimit()).results)
        } catch (e: UnknownHostException) {
            kotlin.Result.failure(Throwable("No internet connections!"))
        } catch (e: SocketTimeoutException) {
            kotlin.Result.failure(Throwable("Bad internet connections!"))
        } catch (e: Exception) {
            kotlin.Result.failure(Throwable("Unknown error!"))
        }
    }
}