package com.example.pokeapp.di

import com.example.pokeapp.data.datasource.remote.ApiService
import com.example.pokeapp.data.repository.local.PokemonLocalRepositoryImpl
import com.example.pokeapp.data.repository.remote.PokemonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePokemonRepository(
        apiService: ApiService,
        repository: PokemonLocalRepositoryImpl
    ): PokemonRepositoryImpl {
        return PokemonRepositoryImpl(
            apiService, repository
        )
    }

}