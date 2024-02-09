package com.ontop.test.rickandmorty.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ontop.test.rickandmorty.domain.model.Character
import com.ontop.test.rickandmorty.domain.repository.IRickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: IRickAndMortyRepository,
) : ViewModel() {

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _characterSelected = MutableStateFlow<Character?>(null)
    val characterSelected = _characterSelected.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val characters: Flow<PagingData<Character>> = _name
        .debounce(1000L)
        .flatMapLatest { query ->
            repository.getCharacters(query)
        }.cachedIn(viewModelScope)

    fun setQuery(name: String) {
        _name.update { name }
    }

    fun selectCharacter(character: Character) {
        _characterSelected.update { character }
    }
}