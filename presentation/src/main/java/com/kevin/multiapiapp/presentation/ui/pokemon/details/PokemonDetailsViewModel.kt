package com.kevin.multiapiapp.presentation.ui.pokemon.details

import androidx.lifecycle.viewModelScope
import com.kevin.multiapiapp.common.base.BaseViewModel
import com.kevin.multiapiapp.common.base.UiState
import com.kevin.multiapiapp.common.result.asResult
import com.kevin.multiapiapp.common.result.doOnFailure
import com.kevin.multiapiapp.common.result.doOnLoading
import com.kevin.multiapiapp.common.result.doOnSuccess
import com.kevin.multiapiapp.domain.model.pokemon.PokemonDetailsDomain
import com.kevin.multiapiapp.domain.usecase.pokemon.GetPokemonDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase
) : BaseViewModel<PokemonDetailsUiState>() {
    override fun createInitialState(): PokemonDetailsUiState = PokemonDetailsUiState()

    fun getPokemonDetails(id: Int) {

        viewModelScope.launch {
            getPokemonDetailsUseCase(id).asResult()
                .doOnLoading {
                    setState { copy(isLoading = true) }
                }
                .doOnSuccess { response ->
                    setState {
                        copy(
                            pokemonDetails = response,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }
                .doOnFailure {
                    setState {
                        copy(
                            errorMessage = it?.message ?: "Unknown error",
                            isLoading = false
                        )
                    }
                }.collect()
        }
    }
}

data class PokemonDetailsUiState(
    val isLoading: Boolean = false,
    val pokemonDetails: PokemonDetailsDomain? = null,
    val errorMessage: String? = null
) : UiState