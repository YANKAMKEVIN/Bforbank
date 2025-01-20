package com.kevin.multiapiapp.presentation.ui.pokemon.list

import androidx.lifecycle.viewModelScope
import com.kevin.multiapiapp.common.base.BaseViewModel
import com.kevin.multiapiapp.common.base.UiState
import com.kevin.multiapiapp.common.result.asResult
import com.kevin.multiapiapp.common.result.doOnFailure
import com.kevin.multiapiapp.common.result.doOnLoading
import com.kevin.multiapiapp.common.result.doOnSuccess
import com.kevin.multiapiapp.domain.model.pokemon.PokemonInfosDomain
import com.kevin.multiapiapp.domain.usecase.pokemon.GetAllPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getAllPokemonUseCase: GetAllPokemonUseCase
) : BaseViewModel<PokemonUiState>() {

    override fun createInitialState(): PokemonUiState = PokemonUiState()

    private var currentOffset = 0
    private var hasMoreData = true

    init {
        loadPokemonList()
    }

    fun loadPokemonList() {
        if (!hasMoreData || state.value.isLoadingMore) return

        viewModelScope.launch {
            getAllPokemonUseCase(offset = currentOffset).asResult()
                .doOnLoading {
                    setState {
                        copy(
                            isLoading = currentOffset == 0,
                            isLoadingMore = currentOffset > 0
                        )
                    }
                }
                .doOnSuccess { response ->
                    val updatedList = state.value.pokemonList + response.results
                    setState {
                        copy(
                            pokemonList = updatedList,
                            isLoading = false,
                            isLoadingMore = false,
                            errorMessage = null
                        )
                    }
                    currentOffset = extractOffsetFromUrl(response.next)
                    hasMoreData = response.next != null
                }
                .doOnFailure {
                    setState {
                        copy(
                            errorMessage = it?.message ?: "Unknown error",
                            isLoading = false,
                            isLoadingMore = false,
                        )
                    }
                }
                .collect()
        }

    }

    private fun extractOffsetFromUrl(url: String?): Int {
        if (url.isNullOrEmpty()) return currentOffset
        val regex = Regex("[?&]offset=(\\d+)")
        val matchResult = regex.find(url)
        return matchResult?.groups?.get(1)?.value?.toIntOrNull() ?: currentOffset
    }

}

data class PokemonUiState(
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val pokemonList: List<PokemonInfosDomain> = emptyList(),
    val errorMessage: String? = null
) : UiState