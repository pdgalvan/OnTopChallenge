package com.ontop.test.rickandmorty.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ontop.test.rickandmorty.presentation.CharacterDetailScreen
import com.ontop.test.rickandmorty.presentation.CharacterScreen
import com.ontop.test.rickandmorty.presentation.CharacterViewModel

@Composable
fun CharacterGraph(
    viewModel: CharacterViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = CharacterNav.List.route,

    ) {
        composable(
            route = CharacterNav.List.route,
        ) {
            CharacterScreen(
                viewModel = viewModel,
                onCharacterSelected = {
                    navController.navigate(CharacterNav.Detail.route)
                }
            )
        }
        composable(
            route = CharacterNav.Detail.route,
        ) {
            CharacterDetailScreen(
                viewModel = viewModel,
            )
        }
    }
}
