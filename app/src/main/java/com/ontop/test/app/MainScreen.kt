package com.ontop.test.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ontop.test.app.navigation.BottomNavItem
import com.ontop.test.app.navigation.MainGraph


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomBar(navController)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            MainGraph(navController)
        }
    }
}

@Composable
fun BottomBar(mainNavController: NavHostController) {

    val items = listOf(
        BottomNavItem.Character,
        BottomNavItem.Phrase,
        BottomNavItem.Password
    )

    NavigationBar {
        val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = item.label)
                },
                selected = currentRoute == item.route,
                onClick = {
                    mainNavController.navigate(item.route) {
                        mainNavController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
            )
        }
    }
}