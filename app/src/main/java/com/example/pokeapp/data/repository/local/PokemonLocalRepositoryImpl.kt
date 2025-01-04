package com.example.pokeapp.data.repository.local

import com.example.pokeapp.data.datasource.local.dao.PokemonDao
import com.example.pokeapp.data.model.Pokemon
import com.example.pokeapp.utils.MapOfMap
import javax.inject.Inject

class PokemonLocalRepositoryImpl @Inject constructor(
    private val dao: PokemonDao
): PokemonLocalRepository {
    override suspend fun getPokemonList(): List<Pokemon> {
        return dao.getAll().filterNotNull().map { MapOfMap.to(it) }
    }

    override suspend fun getPokemon(id: String): Pokemon? {
        val model = dao.get(id.toInt())
        return if (model != null) MapOfMap.to(model) else null
    }

    override suspend fun save(pokemonList: List<Pokemon>) {
        dao.insert(pokemonList.map { MapOfMap.to(it) })
    }
}