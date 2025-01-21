package com.kevin.multiapiapp.data.model.pokemon

import com.google.gson.annotations.SerializedName
import com.kevin.multiapiapp.data.model.pokemon.UltraSunUltraMoon

data class GenerationVii(
    val icons: Icons,
    @SerializedName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: UltraSunUltraMoon
)