package com.kevin.multiapiapp.data.api.pokemon

import com.kevin.multiapiapp.data.model.pokemon.PokemonDetailsResponse
import com.kevin.multiapiapp.data.model.pokemon.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    /**
     * Retrieves a paginated list of Pokémon.
     *
     * @param limit The number of Pokémon to retrieve in a single call.
     * @param offset The index of the first Pokémon to retrieve (for pagination).
     * @return A [PokemonListResponse] containing a list of Pokémon with basic information.
     */
    @GET(BASE_PATH)
    suspend fun getAllPokemon(
        @Query(PARAM_LIMIT) limit: Int,
        @Query(PARAM_OFFSET) offset: Int
    ): PokemonListResponse

    /**
     * Retrieves detailed information about a specific Pokémon by its ID.
     *
     * @param id The unique identifier of the Pokémon.
     * @return A [PokemonDetailsResponse] containing detailed information about the Pokémon.
     */
    @GET("$BASE_PATH/{$PARAM_ID}")
    suspend fun getPokemonDetails(@Path(PARAM_ID) id: Int): PokemonDetailsResponse

    companion object {
        // Paths
        private const val BASE_PATH = "pokemon"

        // Parameters
        private const val PARAM_LIMIT = "limit"
        private const val PARAM_OFFSET = "offset"
        private const val PARAM_ID = "id"
    }
}