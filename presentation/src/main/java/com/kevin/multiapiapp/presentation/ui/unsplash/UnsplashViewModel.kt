package com.kevin.multiapiapp.presentation.ui.unsplash

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.kevin.multiapiapp.common.UNSPLASH_PER_PAGE
import com.kevin.multiapiapp.common.base.BaseViewModel
import com.kevin.multiapiapp.common.base.UiState
import com.kevin.multiapiapp.common.result.asResult
import com.kevin.multiapiapp.common.result.doOnFailure
import com.kevin.multiapiapp.common.result.doOnLoading
import com.kevin.multiapiapp.common.result.doOnSuccess
import com.kevin.multiapiapp.domain.model.unsplash.PhotoDomain
import com.kevin.multiapiapp.domain.usecase.unsplash.SearchUnsplashUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UnsplashViewModel @Inject constructor(
    private val searchUnsplashUseCase: SearchUnsplashUseCase
) : BaseViewModel<UnsplashState>() {

    override fun createInitialState(): UnsplashState = UnsplashState()

    private var hasReachedEnd = false

    fun onQueryChanged(query: String) {
        setState { copy(query = query, photos = emptyList(), page = 1) }
        hasReachedEnd = false
    }

    fun searchPhotos() {
        if (state.value.query.isBlank() || hasReachedEnd) return

        viewModelScope.launch {
            searchUnsplashUseCase(query = state.value.query, page = state.value.page).asResult()
                .doOnLoading {
                    setState { copy(isLoading = true) }
                }
                .doOnSuccess { response ->
                    if (response.results.size < UNSPLASH_PER_PAGE) {
                        hasReachedEnd = true
                    }
                    setState {
                        copy(
                            isLoading = false,
                            photos = state.value.photos + response.results,
                            page = if (response.results.size < UNSPLASH_PER_PAGE) state.value.page else state.value.page + 1

                        )
                    }
                }
                .doOnFailure {
                    setState { copy(isLoading = false) }
                    Log.e("UnsplashViewModel", "Error: ${it?.message}")
                }.collect()
        }
    }
}

data class UnsplashState(
    val photos: List<PhotoDomain> = emptyList(),
    val isLoading: Boolean = false,
    val query: String = "",
    val page: Int = 1
) : UiState