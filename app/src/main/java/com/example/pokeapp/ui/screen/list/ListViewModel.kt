package com.example.pokeapp.ui.screen.list

import android.provider.ContactsContract.Data
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.query
import com.example.pokeapp.data.model.Pokemon
import com.example.pokeapp.data.model.PokemonList
import com.example.pokeapp.data.repository.local.PokemonLocalRepositoryImpl
import com.example.pokeapp.data.repository.remote.PokemonRepositoryImpl
import com.example.pokeapp.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: PokemonRepositoryImpl,
) : ViewModel() {

    var pokemonList: MutableState<DataState<PokemonList>?> = mutableStateOf(null)
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    var isSearching = mutableStateOf(false)
    private var cachedPokemonList = listOf<Pokemon>()
    private var isSearchStarting = true

    fun callPokemonList() {
        viewModelScope.launch {
            isLoading.value = true
            repository.callPokemonList().onEach {
                pokemonList.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun search(name: String) {
        val searching: List<Pokemon> = if (isSearchStarting) {
            (pokemonList.value as DataState.Success).data.results
        } else {
            cachedPokemonList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if (isLoading.value) {
                isLoading.value = false
            }
            if (name.trim().isEmpty()) {
                pokemonList.value = DataState.Success(PokemonList(cachedPokemonList))
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = searching.filter {
                it.name.contains(name.trim(), ignoreCase = true)
            }
            if (isSearchStarting) {
                if (pokemonList.value is DataState.Success) {
                    cachedPokemonList = (pokemonList.value as DataState.Success).data.results
                    isSearchStarting = false
                }
            }
            pokemonList.value = DataState.Success(PokemonList(results))
            isSearching.value = true
        }
    }

}