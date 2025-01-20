package com.kevin.multiapiapp.domain

import com.kevin.multiapiapp.domain.repository.PokemonRepository
import com.kevin.multiapiapp.domain.usecase.pokemon.GetAllPokemonUseCase
import com.kevin.multiapiapp.domain.usecase.pokemon.impl.GetAllPokemonUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Singleton
    @Provides
    fun provideGetAllPokemonUseCase(pokemonRepository: PokemonRepository): GetAllPokemonUseCase =
        GetAllPokemonUseCaseImpl(pokemonRepository)
}