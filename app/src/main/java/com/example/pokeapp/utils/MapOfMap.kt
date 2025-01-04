package com.example.pokeapp.utils

import com.example.pokeapp.data.model.Pokemon
import com.example.pokeapp.data.model.PokemonTable

object MapOfMap {

    fun to(table: PokemonTable) = Pokemon(table.id, table.name)
    fun to(table: Pokemon) = PokemonTable(table.id, table.name)
}