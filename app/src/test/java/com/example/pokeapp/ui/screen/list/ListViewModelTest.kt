package com.example.pokeapp.ui.screen.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pokeapp.data.model.Pokemon
import com.example.pokeapp.data.model.PokemonList
import com.example.pokeapp.data.repository.remote.PokemonRepositoryImpl
import com.example.pokeapp.utils.DataState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations


@OptIn(ExperimentalCoroutinesApi::class)
class ListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var listViewModel: ListViewModel

    private val mockRepository: PokemonRepositoryImpl = mockk()

    private var testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        listViewModel = ListViewModel(mockRepository)

        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun testCallPokemonListSuccess() = runTest {
        val pokemonList = listOf(Pokemon("url","Pikachu"), Pokemon("url","Charmander"))
        val mockPokemonList = DataState.Success(PokemonList(pokemonList))

        coEvery { mockRepository.callPokemonList() } returns flow { emit(mockPokemonList) }

        listViewModel.callPokemonList()

        assertEquals(mockPokemonList, listViewModel.pokemonList.value)
    }

}