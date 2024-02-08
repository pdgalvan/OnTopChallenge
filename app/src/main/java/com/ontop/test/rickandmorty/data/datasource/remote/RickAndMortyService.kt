package com.ontop.test.rickandmorty.data.datasource.remote

import com.ontop.test.rickandmorty.data.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyService {
    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
        private const val CHARACTER = "character/"
        internal object Queries {
            const val PAGE = "page"
            const val NAME = "name"
        }
    }

    @GET(CHARACTER)
    suspend fun getCharacters(
        @Query(Queries.PAGE) page: Int,
        @Query(Queries.NAME) name: String,
    ) : CharacterResponse

}