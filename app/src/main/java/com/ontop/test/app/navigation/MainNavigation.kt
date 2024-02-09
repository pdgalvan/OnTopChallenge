package com.ontop.test.app.navigation

sealed class MainNavigation(val route: String) {
    data object CharacterRoute : MainNavigation("character_route")
    data object PhraseRoute : MainNavigation("phrase_route")
    data object PasswordRoute : MainNavigation("password_route")
}