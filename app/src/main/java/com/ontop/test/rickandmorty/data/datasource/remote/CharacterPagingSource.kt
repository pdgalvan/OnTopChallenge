package com.ontop.test.rickandmorty.data.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ontop.test.rickandmorty.domain.model.Character
import okio.IOException
import retrofit2.HttpException

const val INIT_PAGE_INDEX = 1

class CharacterPagingSource(
    private val service: RickAndMortyService,
    private val name: String,
) : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val currentPage = params.key ?: INIT_PAGE_INDEX
        return try {
            val response = service.getCharacters(page = currentPage, name = name)
            val characters = response.results
            val endOfPaginationReached = characters.isEmpty()
            LoadResult.Page(
                data = characters,
                prevKey = if (currentPage == INIT_PAGE_INDEX || endOfPaginationReached) null else currentPage - 1,
                nextKey = currentPage + 1,
            )

        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return if (currentPage == INIT_PAGE_INDEX) {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null,
                )
            } else {
                LoadResult.Error(e)
            }
        }
    }
}
