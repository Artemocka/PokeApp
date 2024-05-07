package com.dracul.pokeapp.di


import com.dracul.pokeapp.data.api.PokeApi
import com.dracul.pokeapp.data.repository.GetPokemonsImpl
import com.dracul.pokeapp.domain.repository.GetPokemonsRepo
import com.dracul.pokeapp.domain.usecase.GetPokemonsUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
object AppModule {

    @Provides
    fun provideProductApi(): PokeApi {
        val retrofit = Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApi::class.java)
        return retrofit
    }

    @Provides
    fun providePokemonsRepo(api: PokeApi): GetPokemonsRepo {
        return GetPokemonsImpl(api)
    }
    @Provides
    fun provideGetPokemonsUseCase(repository: GetPokemonsRepo): GetPokemonsUseCase {
        return GetPokemonsUseCase(repository)
    }
}
