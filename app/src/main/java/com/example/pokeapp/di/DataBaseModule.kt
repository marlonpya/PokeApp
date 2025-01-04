package com.example.pokeapp.di

import android.content.Context
import androidx.room.Room
import com.example.pokeapp.data.datasource.local.PokemonDataBase
import com.example.pokeapp.data.datasource.local.dao.PokemonDao
import com.example.pokeapp.data.repository.local.PokemonLocalRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PokemonDataBase {
        return Room.databaseBuilder(
            context,
            PokemonDataBase::class.java,
            "pokemondb.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao(database: PokemonDataBase): PokemonDao =
        database.getPokemonDao()

    @Singleton
    @Provides
    fun provideLocalRepo(dao: PokemonDao): PokemonLocalRepositoryImpl =
        PokemonLocalRepositoryImpl(dao)

}