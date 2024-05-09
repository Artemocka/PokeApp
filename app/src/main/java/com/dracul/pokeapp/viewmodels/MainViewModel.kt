package com.dracul.pokeapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Page
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    val getPokemonsUseCase: com.example.domain.usecase.GetPokemonsUseCase,

    ) : ViewModel() {

    val pokemonList = MutableStateFlow<List<com.example.domain.models.Result>>(emptyList())

    val error = MutableSharedFlow<String>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private var page = Page(0)


    init {
        getPokemons()
    }

    private fun getPokemons() {
        viewModelScope.launch {
            val result = getPokemonsUseCase.execute(page)
            result.onFailure {
                it.message?.let { errorMessage ->
                    error.emit(errorMessage)
                }
            }
            result.onSuccess {
                pokemonList.emit(pokemonList.value + it)
            }
        }
    }

    fun nextPage() {
        val index = page.index
        page = page.copy(index = index + 1)
        getPokemons()
    }


}