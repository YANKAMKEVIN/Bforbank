package com.kevin.multiapiapp.data.di

import com.kevin.multiapiapp.data.api.pokemon.PokemonApi
import com.kevin.multiapiapp.data.datasource.pokemon.PokemonDataSource
import com.kevin.multiapiapp.data.datasource.pokemon.impl.PokemonDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun providePokemonDataSource(pokemonApi: PokemonApi): PokemonDataSource {
        return PokemonDataSourceImpl(pokemonApi)
    }
}