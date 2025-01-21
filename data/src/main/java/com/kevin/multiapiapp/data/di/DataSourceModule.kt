package com.kevin.multiapiapp.data.di

import com.kevin.multiapiapp.data.api.pokemon.PokemonApi
import com.kevin.multiapiapp.data.api.spotify.SpotifyApi
import com.kevin.multiapiapp.data.api.unsplash.UnsplashApi
import com.kevin.multiapiapp.data.datasource.pokemon.PokemonDataSource
import com.kevin.multiapiapp.data.datasource.pokemon.impl.PokemonDataSourceImpl
import com.kevin.multiapiapp.data.datasource.spotify.SpotifyDataSource
import com.kevin.multiapiapp.data.datasource.spotify.impl.SpotifyDataSourceImpl
import com.kevin.multiapiapp.data.datasource.unsplash.UnsplashDataSource
import com.kevin.multiapiapp.data.datasource.unsplash.impl.UnsplashDataSourceImpl
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

    @Provides
    @Singleton
    fun provideSpotifyDataSource(spotifyApi: SpotifyApi): SpotifyDataSource {
        return SpotifyDataSourceImpl(spotifyApi)
    }

    @Provides
    @Singleton
    fun provideUnsplashDataSource(unsplashApi: UnsplashApi): UnsplashDataSource {
        return UnsplashDataSourceImpl(unsplashApi)
    }
}