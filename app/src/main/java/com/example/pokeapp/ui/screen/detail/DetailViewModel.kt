package com.example.pokeapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import com.example.pokeapp.data.datasource.local.dao.PokemonDao
import com.example.pokeapp.data.model.Pokemon
import com.example.pokeapp.utils.MapOfMap
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val dao: PokemonDao
): ViewModel() {

    suspend fun getPokemon(id: String): Pokemon? {
        return dao.get(id.toInt()).let { MapOfMap.to(it!!) }
    }
}