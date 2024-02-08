package com.ontop.test.rickandmorty.data

import com.ontop.test.rickandmorty.domain.model.Character

data class CharacterResponse(
    val results: List<Character>,
)

