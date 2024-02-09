package com.ontop.test.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ontop.test.rickandmorty.presentation.navigation.CharacterGraph

@Composable
fun MainGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = MainNavigation.CharacterRoute.route,
    ) {
        composable(
            route = MainNavigation.CharacterRoute.route,
        ) {
            CharacterGraph()
        }
        composable(
            route = MainNavigation.PhraseRoute.route,
        ) {

        }
        composable(
            route = MainNavigation.PasswordRoute.route,
        ) {

        }
    }
}
