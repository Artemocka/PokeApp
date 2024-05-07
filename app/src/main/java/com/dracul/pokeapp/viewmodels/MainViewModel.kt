package com.dracul.pokeapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dracul.pokeapp.di.DaggerInjector
import com.dracul.pokeapp.di.components.main.DaggerMainComponent
import com.dracul.pokeapp.di.components.main.MainDependencies
import com.dracul.pokeapp.domain.models.Result

import com.dracul.pokeapp.domain.usecase.GetPokemonsUseCase
import com.dracul.pokeapp.utills.Page
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel: ViewModel() {
    @Inject
    lateinit var getPokemonsUseCase: GetPokemonsUseCase

    val pokemonList = MutableStateFlow<List<Result>>(emptyList())

    val error = MutableSharedFlow<String>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private var page = Page(0)


    init {
        DaggerMainComponent.builder().dependencies(dependencies = DaggerInjector.appComponent).build().inject(this@MainViewModel)
        getPokemons()
    }
    private fun getPokemons() {

        viewModelScope.launch {
            val pair = getPokemonsUseCase.execute(page)
            when {
                pair.second != null && pokemonList.value.isEmpty() -> {
                    error.emit(pair.second!!)
                    val index = page.index
                    page = page.copy(index = index.dec())
                }

                pair.second != null -> {
                    error.emit(pair.second!!)
                    val index = page.index
                    page = page.copy(index = index.dec())
                }

                pair.second == null -> {
                    pair.first?.run {
                        pokemonList.emit(this)
                    }
                }
            }
        }
    }


}