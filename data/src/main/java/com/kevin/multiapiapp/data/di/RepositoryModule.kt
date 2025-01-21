package com.kevin.multiapiapp.data.di

import com.kevin.multiapiapp.data.datasource.pokemon.PokemonDataSource
import com.kevin.multiapiapp.data.datasource.spotify.SpotifyDataSource
import com.kevin.multiapiapp.data.datasource.unsplash.UnsplashDataSource
import com.kevin.multiapiapp.data.repository.PokemonRepositoryImpl
import com.kevin.multiapiapp.data.repository.SpotifyRepositoryImpl
import com.kevin.multiapiapp.data.repository.UnsplashRepositoryImpl
import com.kevin.multiapiapp.domain.repository.PokemonRepository
import com.kevin.multiapiapp.domain.repository.SpotifyRepository
import com.kevin.multiapiapp.domain.repository.UnsplashRepository
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

    @Provides
    @Singleton
    fun provideSpotifyRepository(spotifyDataSource: SpotifyDataSource): SpotifyRepository {
        return SpotifyRepositoryImpl(spotifyDataSource)
    }

    @Provides
    @Singleton
    fun provideUnsplashRepository(unsplashDataSource: UnsplashDataSource): UnsplashRepository {
        return UnsplashRepositoryImpl(unsplashDataSource)
    }

}
