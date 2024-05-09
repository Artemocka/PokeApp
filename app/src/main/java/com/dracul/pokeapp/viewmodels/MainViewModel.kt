package com.dracul.pokeapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Page
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    val getPokemonsUseCase: com.example.domain.usecase.GetPokemonsUseCase,
    val getPokemonDataUseCase: com.example.domain.usecase.GetPokemonsUseCase,

    ) : ViewModel() {

    val _pokemonList = MutableStateFlow<List<com.example.domain.models.Result>>(emptyList())
    val _error = MutableSharedFlow<String>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    val pokemonList = _pokemonList.asStateFlow()
    val error =_error.asSharedFlow()

    private var page = Page(0)

    init {
        getPokemons()
    }

    private fun getPokemons() {
        viewModelScope.launch {
            val result = getPokemonsUseCase.execute(page)
            result.onFailure {
                it.message?.let { errorMessage ->
                    _error.emit(errorMessage)
                }
            }
            result.onSuccess {
                _pokemonList.emit(_pokemonList.value + it)
            }
        }
    }

    fun nextPage() {
        val index = page.index
        page = page.copy(index = index + 1)
        getPokemons()
    }


}