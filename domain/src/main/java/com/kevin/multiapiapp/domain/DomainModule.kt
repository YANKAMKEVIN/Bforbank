package com.kevin.multiapiapp.domain

import com.kevin.multiapiapp.domain.repository.PokemonRepository
import com.kevin.multiapiapp.domain.repository.SpotifyRepository
import com.kevin.multiapiapp.domain.repository.UnsplashRepository
import com.kevin.multiapiapp.domain.usecase.pokemon.GetAllPokemonUseCase
import com.kevin.multiapiapp.domain.usecase.pokemon.GetPokemonDetailsUseCase
import com.kevin.multiapiapp.domain.usecase.pokemon.impl.GetAllPokemonUseCaseImpl
import com.kevin.multiapiapp.domain.usecase.pokemon.impl.GetPokemonDetailsUseCaseImpl
import com.kevin.multiapiapp.domain.usecase.spotify.SearchTracksUseCase
import com.kevin.multiapiapp.domain.usecase.spotify.impl.SearchTrackUseCaseImpl
import com.kevin.multiapiapp.domain.usecase.unsplash.SearchUnsplashUseCase
import com.kevin.multiapiapp.domain.usecase.unsplash.impl.SearchUnsplashUseCaseImpl
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

    @Singleton
    @Provides
    fun provideGetPokemonDetailsUseCase(pokemonRepository: PokemonRepository): GetPokemonDetailsUseCase =
        GetPokemonDetailsUseCaseImpl(pokemonRepository)

    @Singleton
    @Provides
    fun provideSearchTracksUseCase(spotifyRepository: SpotifyRepository): SearchTracksUseCase =
        SearchTrackUseCaseImpl(spotifyRepository)

    @Singleton
    @Provides
    fun provideSearchUnsplashUseCase(unsplashRepository: UnsplashRepository): SearchUnsplashUseCase =
        SearchUnsplashUseCaseImpl(unsplashRepository)
}