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
    override suspend fun get(page: Page):Pair<List<Result>?,String?>{

        var resultList: List<Result>? = null
        var errorMessage:String? = null

        try {
            resultList= api.getPage(page.getOffset(), page.getLimit()).results
        } catch (e: UnknownHostException) {
            errorMessage ="No internet connections!"
        } catch (e: SocketTimeoutException) {
            errorMessage="Bad internet connections"
        } catch (e: Exception) {
            errorMessage="Unknown error"
        }

        return Pair(resultList, errorMessage)
    }
}