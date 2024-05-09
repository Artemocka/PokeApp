package com.dracul.pokeapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.pokemondata.PokemonData
import com.example.domain.models.pokemondata.Sprites
import com.example.domain.usecase.GetPokemonDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    val getPokemonDataUseCase: GetPokemonDataUseCase,
) : ViewModel() {
    val id = MutableStateFlow(0)
    var pokemonData= MutableStateFlow(PokemonData(
        0,
        0,
        0,
        "",
        0,
        Sprites("","","","","","","",""),
        0,
    ))
    val _error =
        MutableSharedFlow<String>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

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
                pokemonData.value = it
            }
        }
    }

}