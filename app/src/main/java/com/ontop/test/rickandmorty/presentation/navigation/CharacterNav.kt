package com.ontop.test.rickandmorty.presentation.navigation

sealed class CharacterNav(val route: String) {
    data object List : CharacterNav("character_list_screen")
    data object Detail : CharacterNav("character_detail_screen")
}