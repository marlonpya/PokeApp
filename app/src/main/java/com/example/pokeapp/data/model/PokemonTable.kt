package com.example.pokeapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "pokemonTable")
data class PokemonTable(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String
)