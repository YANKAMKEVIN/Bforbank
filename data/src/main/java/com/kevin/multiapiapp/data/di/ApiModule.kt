package com.kevin.multiapiapp.data.di

import com.kevin.multiapiapp.data.api.pokemon.PokemonApi
import com.kevin.multiapiapp.data.api.spotify.SpotifyApi
import com.kevin.multiapiapp.data.api.spotify.SpotifyAuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun providePokemonApi(): PokemonApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_POKEMON)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSpotifyAuthApi(): SpotifyAuthApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_SPOTIFY_AUTH)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpotifyAuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSpotifyApi(okHttpClient: OkHttpClient): SpotifyApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_SPOTIFY)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(SpotifyApi::class.java)
    }

    companion object {
        private const val BASE_URL_POKEMON = "https://pokeapi.co/api/v2/"
        private const val BASE_URL_SPOTIFY_AUTH = "https://accounts.spotify.com/"
        private const val BASE_URL_SPOTIFY = "https://api.spotify.com/"
    }
}
