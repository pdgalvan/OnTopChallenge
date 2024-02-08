package com.ontop.test.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.ontop.test.rickandmorty.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface IRickAndMortyRepository {
    fun getCharacters(name: String): Flow<PagingData<Character>>
}