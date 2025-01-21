package com.kevin.multiapiapp.data.mapper.pokemon

import com.kevin.multiapiapp.data.model.pokemon.Ability
import com.kevin.multiapiapp.data.model.pokemon.PokemonDetailsResponse
import com.kevin.multiapiapp.data.model.pokemon.Showdown
import com.kevin.multiapiapp.data.model.pokemon.Sprites
import com.kevin.multiapiapp.data.model.pokemon.Stat
import com.kevin.multiapiapp.data.model.pokemon.TypeXX
import com.kevin.multiapiapp.domain.model.pokemon.AbilityDomain
import com.kevin.multiapiapp.domain.model.pokemon.PokemonDetailsDomain
import com.kevin.multiapiapp.domain.model.pokemon.ShowdownDomain
import com.kevin.multiapiapp.domain.model.pokemon.SpritesDomain
import com.kevin.multiapiapp.domain.model.pokemon.StatDomain
import com.kevin.multiapiapp.domain.model.pokemon.TypeDomain

object PokemonDetailsMapper {

    private fun Showdown.toDomain(): ShowdownDomain {
        return ShowdownDomain(
            front_default = this.front_default,
        )
    }

    private fun Sprites.toDomain(): SpritesDomain {
        return SpritesDomain(
            back_default = this.back_default,
            front_default = this.front_default,
            showdown = this.other.showdown.toDomain()
        )
    }

    private fun Stat.toDomain(): StatDomain {
        return StatDomain(
            base_stat = this.base_stat,
        )
    }

    private fun TypeXX.toDomain(): TypeDomain {
        return TypeDomain(
            type = this.type.name
        )
    }

    private fun Ability.toDomain(): AbilityDomain {
        return AbilityDomain(
            ability = this.ability.name
        )
    }

    // Extension function to map PokemonResult to PokemonInfosDomain
    fun mapToDomain(response: PokemonDetailsResponse): PokemonDetailsDomain {
        return PokemonDetailsDomain(
            name = response.name,
            sprites = response.sprites.toDomain(),
            cries = response.cries.latest,
            height = response.height,
            weight = response.weight,
            stats = response.stats.map { it.toDomain() },
            types = response.types.map { it.toDomain() },
            abilities = response.abilities.map { it.toDomain() }
        )
    }
}
