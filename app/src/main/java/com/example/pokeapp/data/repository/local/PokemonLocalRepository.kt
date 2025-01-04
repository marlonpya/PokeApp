package com.example.pokeapp.data.repository.local

import com.example.pokeapp.data.model.Pokemon

interface PokemonLocalRepository {

    suspend fun getPokemonList(): List<Pokemon>
    suspend fun getPokemon(id: String): Pokemon?
    suspend fun save(pokemonList: List<Pokemon>)
}