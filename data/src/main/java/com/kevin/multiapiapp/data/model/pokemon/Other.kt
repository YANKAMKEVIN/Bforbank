package com.kevin.multiapiapp.data.model.pokemon

import com.google.gson.annotations.SerializedName
import com.kevin.multiapiapp.data.model.pokemon.Showdown

data class Other(
    val dream_world: DreamWorld,
    val home: Home,
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork,
    val showdown: Showdown
)