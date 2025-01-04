package com.example.pokeapp.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokeapp.data.model.PokemonTable

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<PokemonTable>)

    @Query("Select * From pokemonTable Where id = :id")
    suspend fun get(id: Int): PokemonTable?

    @Query("SELECT * FROM pokemonTable")
    suspend fun getAll(): List<PokemonTable?>

    @Query("SELECT * FROM pokemonTable WHERE name LIKE :name")
    suspend fun searchPokemon(name: String): List<PokemonTable?>
}