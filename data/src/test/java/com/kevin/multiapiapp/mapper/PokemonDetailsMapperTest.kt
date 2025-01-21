package com.kevin.multiapiapp.mapper

import com.kevin.multiapiapp.data.mapper.pokemon.PokemonDetailsMapper
import com.kevin.multiapiapp.data.model.pokemon.Ability
import com.kevin.multiapiapp.data.model.pokemon.AbilityX
import com.kevin.multiapiapp.data.model.pokemon.Animated
import com.kevin.multiapiapp.data.model.pokemon.BlackWhite
import com.kevin.multiapiapp.data.model.pokemon.Cries
import com.kevin.multiapiapp.data.model.pokemon.Crystal
import com.kevin.multiapiapp.data.model.pokemon.DiamondPearl
import com.kevin.multiapiapp.data.model.pokemon.DreamWorld
import com.kevin.multiapiapp.data.model.pokemon.Emerald
import com.kevin.multiapiapp.data.model.pokemon.FireredLeafgreen
import com.kevin.multiapiapp.data.model.pokemon.GenerationI
import com.kevin.multiapiapp.data.model.pokemon.GenerationIi
import com.kevin.multiapiapp.data.model.pokemon.GenerationIii
import com.kevin.multiapiapp.data.model.pokemon.GenerationIv
import com.kevin.multiapiapp.data.model.pokemon.GenerationV
import com.kevin.multiapiapp.data.model.pokemon.GenerationVi
import com.kevin.multiapiapp.data.model.pokemon.GenerationVii
import com.kevin.multiapiapp.data.model.pokemon.GenerationViii
import com.kevin.multiapiapp.data.model.pokemon.Gold
import com.kevin.multiapiapp.data.model.pokemon.HeartgoldSoulsilver
import com.kevin.multiapiapp.data.model.pokemon.Home
import com.kevin.multiapiapp.data.model.pokemon.Icons
import com.kevin.multiapiapp.data.model.pokemon.OfficialArtwork
import com.kevin.multiapiapp.data.model.pokemon.OmegarubyAlphasapphire
import com.kevin.multiapiapp.data.model.pokemon.Other
import com.kevin.multiapiapp.data.model.pokemon.Platinum
import com.kevin.multiapiapp.data.model.pokemon.PokemonDetailsResponse
import com.kevin.multiapiapp.data.model.pokemon.RedBlue
import com.kevin.multiapiapp.data.model.pokemon.RubySapphire
import com.kevin.multiapiapp.data.model.pokemon.Showdown
import com.kevin.multiapiapp.data.model.pokemon.Silver
import com.kevin.multiapiapp.data.model.pokemon.Species
import com.kevin.multiapiapp.data.model.pokemon.Sprites
import com.kevin.multiapiapp.data.model.pokemon.Stat
import com.kevin.multiapiapp.data.model.pokemon.StatX
import com.kevin.multiapiapp.data.model.pokemon.TypeX
import com.kevin.multiapiapp.data.model.pokemon.TypeXX
import com.kevin.multiapiapp.data.model.pokemon.UltraSunUltraMoon
import com.kevin.multiapiapp.data.model.pokemon.Versions
import com.kevin.multiapiapp.data.model.pokemon.XY
import com.kevin.multiapiapp.data.model.pokemon.Yellow
import com.kevin.multiapiapp.domain.model.pokemon.PokemonDetailsDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class PokemonDetailsMapperTest {

    @Test
    fun `mapToDomain should correctly map PokemonDetailsResponse to PokemonDetailsDomain`() {
        // Arrange: Create a PokemonDetailsResponse with test data
        val pokemonDetailsResponse = PokemonDetailsResponse(
            name = "Pikachu",
            sprites = Sprites(
                back_default = "back_default_url",
                back_female = "back_female",
                back_shiny = "back_shiny_url",
                back_shiny_female = "back_shiny_female",
                front_default = "front_default_url",
                front_female = "front_female",
                front_shiny = "front_shiny_url",
                front_shiny_female = "front_shiny_female",
                other = Other(
                    dream_world = DreamWorld(
                        front_default = "dream_world_front_url",
                        front_female = "front_female"
                    ),
                    home = Home(
                        front_default = "home_front_url",
                        front_female = "front_female",
                        front_shiny = "home_shiny_front_url",
                        front_shiny_female = "front_shiny_female"
                    ),
                    officialArtwork = OfficialArtwork(
                        front_default = "artwork_front_url",
                        front_shiny = "artwork_shiny_front_url"
                    ),
                    showdown = Showdown(
                        back_default = "showdown_back_default_url",
                        back_female = "back_female",
                        back_shiny = "showdown_back_shiny_url",
                        back_shiny_female = "back_shiny_female",
                        front_default = "showdown_front_default_url",
                        front_female = "front_female",
                        front_shiny = "showdown_front_shiny_url",
                        front_shiny_female = "front_shiny_female"
                    )
                ),
                versions = Versions(
                    generationI = GenerationI(
                        redBlue = RedBlue(
                            back_default = "generationI_redBlue_back_default",
                            back_gray = "generationI_redBlue_back_gray",
                            front_default = "generationI_redBlue_front_default",
                            front_gray = "generationI_redBlue_front_gray"
                        ),
                        yellow = Yellow(
                            back_default = "generationI_yellow_back_default",
                            back_gray = "generationI_yellow_back_gray",
                            front_default = "generationI_yellow_front_default",
                            front_gray = "generationI_yellow_front_gray"
                        )
                    ),
                    generationII = GenerationIi(
                        crystal = Crystal(
                            back_default = "generationII_crystal_back_default",
                            back_shiny = "generationII_crystal_back_shiny",
                            front_default = "generationII_crystal_front_default",
                            front_shiny = "generationII_crystal_front_shiny"
                        ),
                        gold = Gold(
                            back_default = "generationII_gold_back_default",
                            back_shiny = "generationII_gold_back_shiny",
                            front_default = "generationII_gold_front_default",
                            front_shiny = "generationII_gold_front_shiny"
                        ),
                        silver = Silver(
                            back_default = "generationII_silver_back_default",
                            back_shiny = "generationII_silver_back_shiny",
                            front_default = "generationII_silver_front_default",
                            front_shiny = "generationII_silver_front_shiny"
                        )
                    ),
                    generationIII = GenerationIii(
                        emerald = Emerald(
                            front_default = "generationIII_emerald_front_default",
                            front_shiny = "generationIII_emerald_front_shiny"
                        ),
                        fireredLeafgreen = FireredLeafgreen(
                            back_default = "generationIII_fireredLeafgreen_back_default",
                            back_shiny = "generationIII_fireredLeafgreen_back_shiny",
                            front_default = "generationIII_fireredLeafgreen_front_default",
                            front_shiny = "generationIII_fireredLeafgreen_front_shiny"
                        ),
                        rubySapphire = RubySapphire(
                            back_default = "generationIII_rubySapphire_back_default",
                            back_shiny = "generationIII_rubySapphire_back_shiny",
                            front_default = "generationIII_rubySapphire_front_default",
                            front_shiny = "generationIII_rubySapphire_front_shiny"
                        )
                    ),
                    generationIV = GenerationIv(
                        diamondPearl = DiamondPearl(
                            back_default = "generationIV_diamondPearl_back_default",
                            back_shiny = "generationIV_diamondPearl_back_shiny",
                            front_default = "generationIV_diamondPearl_front_default",
                            front_shiny = "generationIV_diamondPearl_front_shiny",
                            back_female = "back_female",
                            front_female = "front_female",
                            back_shiny_female = "back_shiny_female",
                            front_shiny_female = "front_shiny_female"
                        ),
                        heartgoldSoulsilver = HeartgoldSoulsilver(
                            back_default = "generationIV_heartgoldSoulsilver_back_default",
                            back_shiny = "generationIV_heartgoldSoulsilver_back_shiny",
                            front_default = "generationIV_heartgoldSoulsilver_front_default",
                            front_shiny = "generationIV_heartgoldSoulsilver_front_shiny",
                            back_female = "back_female",
                            front_female = "front_female",
                            back_shiny_female = "back_shiny_female",
                            front_shiny_female = "front_shiny_female"
                        ),
                        platinum = Platinum(
                            back_default = "generationIV_platinum_back_default",
                            back_shiny = "generationIV_platinum_back_shiny",
                            front_default = "generationIV_platinum_front_default",
                            front_shiny = "generationIV_platinum_front_shiny",
                            back_female = "back_female",
                            front_female = "front_female",
                            back_shiny_female = "back_shiny_female",
                            front_shiny_female = "front_shiny_female"
                        )
                    ),
                    generationV = GenerationV(
                        blackWhite = BlackWhite(
                            back_default = "generationV_blackWhite_back_default",
                            back_shiny = "generationV_blackWhite_back_shiny",
                            front_default = "generationV_blackWhite_front_default",
                            front_shiny = "generationV_blackWhite_front_shiny",
                            back_female = "back_female",
                            front_female = "front_female",
                            back_shiny_female = "back_shiny_female",
                            front_shiny_female = "front_shiny_female",
                            animated = Animated(
                                back_default = "generationV_blackWhite_back_default",
                                back_shiny = "generationV_blackWhite_back_shiny",
                                front_default = "generationV_blackWhite_front_default",
                                front_shiny = "generationV_blackWhite_front_shiny",
                                back_female = "back_female",
                                front_female = "front_female",
                                back_shiny_female = "back_shiny_female",
                                front_shiny_female = "front_shiny_female",
                            )
                        )
                    ),
                    generationVI = GenerationVi(
                        omegarubyAlphasapphire = OmegarubyAlphasapphire(
                            front_female = "generationVI_omegarubyAlphasapphire_back_default",
                            front_shiny_female = "generationVI_omegarubyAlphasapphire_back_shiny",
                            front_default = "generationVI_omegarubyAlphasapphire_front_default",
                            front_shiny = "generationVI_omegarubyAlphasapphire_front_shiny"
                        ),
                        xy = XY(
                            front_female = "generationVI_omegarubyAlphasapphire_back_default",
                            front_shiny_female = "generationVI_omegarubyAlphasapphire_back_shiny",
                            front_default = "generationVI_omegarubyAlphasapphire_front_default",
                            front_shiny = "generationVI_omegarubyAlphasapphire_front_shiny"
                        )
                    ),
                    generationVII = GenerationVii(
                        icons = Icons(
                            front_default = "generationVII_icons_front_default",
                            front_female = "front_female"
                        ),
                        ultraSunUltraMoon = UltraSunUltraMoon(
                            front_female = "generationVI_omegarubyAlphasapphire_back_default",
                            front_shiny_female = "generationVI_omegarubyAlphasapphire_back_shiny",
                            front_default = "generationVI_omegarubyAlphasapphire_front_default",
                            front_shiny = "generationVI_omegarubyAlphasapphire_front_shiny"
                        )
                    ),
                    generationVIII = GenerationViii(
                        icons = Icons(
                            front_default = "generationVIII_icons_front_default",
                            front_female = "front_female"
                        )
                    )
                )
            ),
            cries = Cries(latest = "pikachu_cry.mp3", legacy = ""),
            height = 4,
            weight = 60,
            stats = listOf(
                Stat(base_stat = 35, stat = StatX(name = "hp", url = "url"), effort = 0),
                Stat(base_stat = 55, stat = StatX(name = "hp", url = "url"), effort = 0),
            ),
            types = listOf(
                TypeXX(type = TypeX(name = "Electric", url = "url"), slot = 1)
            ),
            abilities = listOf(
                Ability(
                    ability = AbilityX(name = "Static", url = "url"),
                    is_hidden = false,
                    slot = 1
                ),
                Ability(
                    ability = AbilityX(name = "Lightning Rod", url = "url"),
                    is_hidden = true,
                    slot = 3
                )
            ),
            is_default = false,
            location_area_encounters = "location_area_encounters_url",
            base_experience = 1,
            id = 1,
            order = 1,
            past_types = emptyList(),
            species = Species(name = "Pikachu", url = "url"),
            moves = emptyList(),
            game_indices = emptyList(),
            held_items = emptyList(),
            forms = emptyList()
        )

        // Act: Map the response to a domain object
        val result: PokemonDetailsDomain = PokemonDetailsMapper.mapToDomain(pokemonDetailsResponse)

        // Assert: Verify the mapping results
        assertEquals("Pikachu", result.name)

        // Verify Sprites mapping
        assertEquals("back_default_url", result.sprites.back_default)
        assertEquals("front_default_url", result.sprites.front_default)
        assertEquals("showdown_front_default_url", result.sprites.showdown.front_default)

        // Verify Cries mapping
        assertEquals("pikachu_cry.mp3", result.cries)

        // Verify Height and Weight mapping
        assertEquals(4, result.height)
        assertEquals(60, result.weight)

        // Verify Stats mapping
        assertEquals(2, result.stats.size)
        assertEquals(35, result.stats[0].base_stat)
        assertEquals(55, result.stats[1].base_stat)

        // Verify Types mapping
        assertEquals(1, result.types.size)
        assertEquals("Electric", result.types[0].type)

        // Verify Abilities mapping
        assertEquals(2, result.abilities.size)
        assertEquals("Static", result.abilities[0].ability)
        assertEquals("Lightning Rod", result.abilities[1].ability)
    }
}
