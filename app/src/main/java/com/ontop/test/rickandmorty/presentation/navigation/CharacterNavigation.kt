package com.ontop.test.rickandmorty.presentation.navigation

sealed class CharacterNavigation(val route: String) {
    data object List : CharacterNavigation("character_list_screen")
    data object Detail : CharacterNavigation("character_detail_screen")
}