package com.kevin.multiapiapp.presentation.ui.mapbox

import androidx.lifecycle.viewModelScope
import com.kevin.multiapiapp.common.base.BaseViewModel
import com.kevin.multiapiapp.common.base.UiState
import com.kevin.multiapiapp.common.result.asResult
import com.kevin.multiapiapp.common.result.doOnFailure
import com.kevin.multiapiapp.common.result.doOnLoading
import com.kevin.multiapiapp.common.result.doOnSuccess
import com.kevin.multiapiapp.common.utils.generateSessionToken
import com.kevin.multiapiapp.domain.model.mapbox.MapboxRetrieveDomain
import com.kevin.multiapiapp.domain.model.mapbox.SuggestionDomain
import com.kevin.multiapiapp.domain.usecase.mapbox.RetrieveLocationDetailsUseCase
import com.kevin.multiapiapp.domain.usecase.mapbox.SearchLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapboxViewModel @Inject constructor(
    private val searchLocationUseCase: SearchLocationUseCase,
    private val retrieveLocationDetailsUseCase: RetrieveLocationDetailsUseCase
) : BaseViewModel<MapboxUiState>() {

    override fun createInitialState(): MapboxUiState = MapboxUiState()
    private val sessionToken = generateSessionToken()

    fun onSuggestionSelected(suggestion: SuggestionDomain) {
        setState {
            copy(selectedSuggestion = suggestion, query = suggestion.address ?: suggestion.name)
        }
        retrieveLocation(suggestion.mapbox_id)
    }

    fun search(query: String) {
        viewModelScope.launch {
            searchLocationUseCase(query = query, sessionToken = sessionToken).asResult()
                .doOnLoading {
                    setState {
                        copy(isLoading = true)
                    }
                }
                .doOnSuccess { response ->
                    setState {
                        copy(
                            suggestions = response.suggestions,
                            isLoading = false
                        )
                    }
                }
                .doOnFailure {
                    setState {
                        copy(errorMessage = it?.message, isLoading = false)
                    }
                }.collect()
        }
    }

    private fun retrieveLocation(id: String) {
        viewModelScope.launch {
            retrieveLocationDetailsUseCase(id, sessionToken).asResult()
                .doOnLoading {
                    setState {
                        copy(isLoading = true)
                    }
                }
                .doOnSuccess { response ->
                    setState {
                        copy(
                            locationDetails = response,
                            isLoading = false
                        )
                    }
                }
                .doOnFailure {
                    setState {
                        copy(errorMessage = it?.message, isLoading = false)
                    }
                }.collect()
        }
    }

    fun onQueryChanged(query: String) {
        setState {
            copy(query = query, selectedSuggestion = null, errorMessage = null)
        }
    }
}


data class MapboxUiState(
    val suggestions: List<SuggestionDomain> = emptyList(),
    val selectedSuggestion: SuggestionDomain? = null,
    val isLoading: Boolean = false,
    val locationDetails: MapboxRetrieveDomain? = null,
    val errorMessage: String? = null,
    val query: String = ""
) : UiState