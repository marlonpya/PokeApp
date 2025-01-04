package com.example.pokeapp.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokeapp.data.datasource.local.dao.PokemonDao
import com.example.pokeapp.data.model.PokemonTable


@Database(version = 1, entities = [PokemonTable::class], exportSchema = false)
abstract class PokemonDataBase : RoomDatabase() {
    abstract fun getPokemonDao() : PokemonDao
}