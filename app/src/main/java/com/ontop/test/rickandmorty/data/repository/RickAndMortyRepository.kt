package com.ontop.test.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ontop.test.rickandmorty.data.datasource.remote.CharacterPagingSource
import com.ontop.test.rickandmorty.data.datasource.remote.RickAndMortyService
import com.ontop.test.rickandmorty.domain.model.Character
import com.ontop.test.rickandmorty.domain.repository.IRickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RickAndMortyRepository @Inject constructor(
    private val service: RickAndMortyService,
) : IRickAndMortyRepository {
    override fun getCharacters(name: String): Flow<PagingData<Character>> {

        return Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = {
                CharacterPagingSource(
                    service = service,
                    name = name,
                )
            }
        ).flow
    }
}