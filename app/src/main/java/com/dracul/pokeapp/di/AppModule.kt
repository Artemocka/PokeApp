package com.dracul.pokeapp.di


import com.dracul.pokeapp.viewmodels.DetailsViewModel
import com.dracul.pokeapp.viewmodels.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    viewModel {
        MainViewModel(
            getPokemonsUseCase = get(),
            context = androidContext(),
            )
    }
    viewModel {
        DetailsViewModel(
            getPokemonDataUseCase = get(),
            context = androidContext(),
        )
    }

}


