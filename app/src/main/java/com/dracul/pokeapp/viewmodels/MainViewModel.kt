package com.dracul.pokeapp.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dracul.pokeapp.ui.main.MainFragmentDirections
import com.dracul.pokeapp.utills.getErrorMessage
import com.example.domain.models.Page
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    val getPokemonsUseCase: com.example.domain.usecase.GetPokemonsUseCase,
    val context: Context,
) : ViewModel() {

    private val _pokemonList = MutableStateFlow<List<com.example.domain.models.Result>>(emptyList())
    private val _error = MutableSharedFlow<String>(replay = 0)

    val pokemonList = _pokemonList.asStateFlow()
    val error = _error.asSharedFlow()

    private var page = Page(0)

    init {
        getPokemons()
    }

    private fun getPokemons() {
        viewModelScope.launch {
            val result = getPokemonsUseCase.execute(page)
            result.onFailure { throwable ->
                _error.emit(context.getErrorMessage(throwable))
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

    fun navigateToDetails(navController: NavController, id: Int) {
        val action = MainFragmentDirections.actionProfile(id)
        navController.navigate(action)
    }


}