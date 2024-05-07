package com.dracul.pokeapp.data.repository

import com.dracul.pokeapp.data.api.PokeApi
import com.dracul.pokeapp.domain.models.Result
import com.dracul.pokeapp.domain.repository.GetPokemonsRepo
import com.dracul.pokeapp.utills.Page
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class GetPokemonsImpl @Inject constructor(
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