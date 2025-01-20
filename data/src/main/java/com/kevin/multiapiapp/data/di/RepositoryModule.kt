package com.kevin.multiapiapp.data.di

import com.kevin.multiapiapp.data.datasource.pokemon.PokemonDataSource
import com.kevin.multiapiapp.data.repository.PokemonRepositoryImpl
import com.kevin.multiapiapp.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun providePokemonRepository(pokemonDataSource: PokemonDataSource): PokemonRepository {
        return PokemonRepositoryImpl(pokemonDataSource)
    }
}
