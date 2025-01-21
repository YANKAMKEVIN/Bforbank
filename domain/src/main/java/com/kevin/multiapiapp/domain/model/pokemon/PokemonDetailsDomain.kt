package com.kevin.multiapiapp.domain.model.pokemon

data class PokemonDetailsDomain(
    val name: String,
    val sprites: SpritesDomain,
    val cries: String,
    val height: Int,
    val weight: Int,
    val stats: List<StatDomain>,
    val types: List<TypeDomain>,

    val abilities: List<AbilityDomain>,
)

data class SpritesDomain(
    val back_default: String,
    val front_default: String,
    val showdown: ShowdownDomain,
)

data class ShowdownDomain(
    val front_default: String,
)

data class StatDomain(
    val base_stat: Int,
)

data class TypeDomain(
    val type: String
)

data class AbilityDomain(
    val ability: String,
)