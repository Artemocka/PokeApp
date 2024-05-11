package com.dracul.pokeapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.domain.models.pokemondata.PokemonData
import com.example.domain.usecase.GetPokemonDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    val getPokemonDataUseCase: GetPokemonDataUseCase,
) : ViewModel() {
    val id = MutableStateFlow(0)
    var _pokemonData= MutableStateFlow<PokemonData?>(null)
    var pokemonData= _pokemonData.asStateFlow()
    private val _error = MutableSharedFlow<String>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val error = _error.asSharedFlow()
    init {
        viewModelScope.launch {
            id.collect{
                getPokemonData()
            }
        }
    }

    fun setId(id: Int) {
        this.id.value = id
    }
    private fun getPokemonData(){
        viewModelScope.launch(Dispatchers.Main.immediate){
            val result = getPokemonDataUseCase.execute(id.value)
            result.onFailure {
                it.message?.let { errorMessage ->
                    _error.emit(errorMessage)
                }
            }
            result.onSuccess {
                _pokemonData.value = it
            }
        }
    }

    fun navigateBack(navController: NavController) {
        navController.popBackStack()
    }

}