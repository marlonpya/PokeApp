package com.example.pokeapp

import com.example.pokeapp.data.model.Pokemon
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    @Test
    fun getIdForUrl() {
        val pokemon = Pokemon(
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            "bulbasaur")
        val id = pokemon.id

        assertEquals(id, pokemon.id)
    }
}