package com.dracul.pokeapp.di


import com.dracul.pokeapp.domain.usecase.GetPokemonsUseCase
import com.dracul.pokeapp.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single<GetPokemonsUseCase> {
        GetPokemonsUseCase(get())
    }
    viewModel { MainViewModel(get()) }

}


