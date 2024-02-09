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
        startDestination = CharacterNavigation.List.route,
    ) {
        composable(
            route = CharacterNavigation.List.route,
        ) {
            CharacterScreen(
                viewModel = viewModel,
                onCharacterSelected = {
                    navController.navigate(CharacterNavigation.Detail.route)
                }
            )
        }
        composable(
            route = CharacterNavigation.Detail.route,
        ) {
            CharacterDetailScreen(
                viewModel = viewModel,
            )
        }
    }
}
