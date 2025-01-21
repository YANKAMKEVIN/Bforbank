package com.kevin.multiapiapp.domain.model.pokemon


data class PokemonListDomain(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonInfosDomain>
)

data class PokemonInfosDomain(
    val name: String,
    val url: String
)