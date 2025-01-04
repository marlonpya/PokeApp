package com.example.pokeapp.data.repository.remote

import com.example.pokeapp.data.datasource.remote.ApiService
import com.example.pokeapp.data.model.PokemonList
import com.example.pokeapp.data.repository.local.PokemonLocalRepositoryImpl
import com.example.pokeapp.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

open class PokemonRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val repoLocal: PokemonLocalRepositoryImpl
) : PokemonRepository {
    companion object {
        const val FIRST_GENERATION = 150
    }

    override suspend fun callPokemonList(): Flow<DataState<PokemonList>> = flow {
        emit(DataState.Loading)
        try {
            val pokemonList = apiService.getPokemonList(FIRST_GENERATION)
            repoLocal.save(pokemonList.results)
            emit(DataState.Success(pokemonList))
        } catch (e: Exception) {
            val listLocal = repoLocal.getPokemonList()
            if (listLocal.isEmpty()) {
                emit(DataState.Error(e))
            } else {
                emit(DataState.Success(PokemonList(repoLocal.getPokemonList())))
            }
        }
    }
}