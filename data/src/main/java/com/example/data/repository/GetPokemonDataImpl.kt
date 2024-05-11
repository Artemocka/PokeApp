package com.example.data.repository

import com.example.data.api.PokeApi
import com.example.domain.models.pokemondata.PokemonData
import com.example.domain.repository.GetPokemonDataRepo
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class GetPokemonDataImpl(
    val api: PokeApi,
) : GetPokemonDataRepo {
    override suspend fun get(id:Int):Result<PokemonData>{
        return try {
            Result.success(api.getPokemonData(id))
        } catch (e: UnknownHostException) {
            Result.failure(e)
        } catch (e: SocketTimeoutException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}