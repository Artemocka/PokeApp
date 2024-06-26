package com.dracul.pokeapp.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dracul.pokeapp.ui.State
import com.dracul.pokeapp.utills.getErrorMessage
import com.example.domain.models.pokemondata.PokemonData
import com.example.domain.usecase.GetPokemonDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    val getPokemonDataUseCase: GetPokemonDataUseCase,
    val context: Context
) : ViewModel() {
    val id = MutableStateFlow<Int?>(null)
    private var _pokemonData = MutableStateFlow<PokemonData?>(null)
    private var _state = MutableStateFlow<State>(State.Loading)
    private val _error = MutableSharedFlow<String>(replay = 0)
    var pokemonData = _pokemonData.asStateFlow()
    val error = _error.asSharedFlow()
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            id.collect {
                it?.let {
                    getPokemonData()
                }
            }
        }
    }

    fun setId(id: Int) {
        this.id.value = id
    }

    private fun getPokemonData() {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            id.value?.let {
                val result = getPokemonDataUseCase.execute(it)
                result.onFailure { throwable ->
                    _error.emit(context.getErrorMessage(throwable))
                    _state.emit(State.Error)
                }
                result.onSuccess { tempPokemonData ->
                    _pokemonData.value = tempPokemonData
                    _state.emit(State.Loaded)
                }
            }
        }
    }

    fun reload() {
        getPokemonData()
    }

    fun navigateBack(navController: NavController) {
        navController.popBackStack()
    }

}