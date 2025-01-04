package com.example.pokeapp.data.datasource.remote

import com.example.pokeapp.data.model.PokemonList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int
    ): PokemonList

}