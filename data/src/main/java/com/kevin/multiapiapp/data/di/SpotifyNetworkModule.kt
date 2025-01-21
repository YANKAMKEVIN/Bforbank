package com.kevin.multiapiapp.data.di

import com.kevin.multiapiapp.data.api.spotify.SpotifyAuthApi
import com.kevin.multiapiapp.data.api.spotify.SpotifyAuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SpotifyNetworkModule {

    @Provides
    @Singleton
    fun provideSpotifyAuthInterceptor(spotifyAuthApi: SpotifyAuthApi): SpotifyAuthInterceptor {
        return SpotifyAuthInterceptor(spotifyAuthApi)
    }

    @Provides
    @Singleton
    fun provideSpotifyHttpClient(spotifyAuthInterceptor: SpotifyAuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(spotifyAuthInterceptor)
            .build()
    }
}
