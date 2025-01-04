package com.example.pokeapp.data.repository.remote

import com.example.pokeapp.data.model.PokemonList
import com.example.pokeapp.utils.DataState
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun callPokemonList(): Flow<DataState<PokemonList>>
}