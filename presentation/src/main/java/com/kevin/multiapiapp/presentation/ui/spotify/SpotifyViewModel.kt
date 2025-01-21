package com.kevin.multiapiapp.presentation.ui.spotify

import androidx.lifecycle.viewModelScope
import com.kevin.multiapiapp.common.SPOTIFY_LIMIT_PER_PAGE
import com.kevin.multiapiapp.common.base.BaseViewModel
import com.kevin.multiapiapp.common.base.UiState
import com.kevin.multiapiapp.common.result.asResult
import com.kevin.multiapiapp.common.result.doOnFailure
import com.kevin.multiapiapp.common.result.doOnLoading
import com.kevin.multiapiapp.common.result.doOnSuccess
import com.kevin.multiapiapp.domain.model.spotify.TrackDomain
import com.kevin.multiapiapp.domain.usecase.spotify.SearchTracksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpotifyViewModel @Inject constructor(
    private val searchTracksUseCase: SearchTracksUseCase
) : BaseViewModel<SpotifyUiState>() {

    override fun createInitialState(): SpotifyUiState = SpotifyUiState()

    private var currentOffset = 0
    private var hasMoreData = true

    fun searchTracks(query: String) {
        if (!hasMoreData || state.value.isLoadingMore) return
        viewModelScope.launch {
            searchTracksUseCase(query, currentOffset).asResult()
                .doOnLoading {
                    setState {
                        copy(
                            isLoading = currentOffset == 0,
                            isLoadingMore = currentOffset > 0
                        )
                    }
                }.doOnSuccess { response ->
                    val newTracks = response.tracks.items
                    val hasMoreData =
                        response.tracks.items.size == SPOTIFY_LIMIT_PER_PAGE
                    setState {
                        copy(
                            tracks =
                            if (currentOffset == 0) {
                                newTracks
                            } else {
                                state.value.tracks + newTracks
                            },
                            isLoading = false,
                            isLoadingMore = false,
                        )
                    }
                    currentOffset =
                        if (hasMoreData) currentOffset + SPOTIFY_LIMIT_PER_PAGE else currentOffset
                }.doOnFailure {
                    setState {
                        copy(
                            errorMessage = it?.message ?: "An unknown error occurred",
                            isLoading = false,
                            isLoadingMore = false
                        )
                    }
                }.collect()
        }
    }

    fun onQueryChanged(query: String) {
        setState { copy(query = query, tracks = emptyList(), errorMessage = null) }
        hasMoreData = true
        currentOffset = 0
    }
}

data class SpotifyUiState(
    val tracks: List<TrackDomain> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val errorMessage: String? = null,
    val query: String = ""
) : UiState