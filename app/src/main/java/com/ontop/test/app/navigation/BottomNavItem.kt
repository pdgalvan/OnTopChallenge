package com.ontop.test.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    data object Character : BottomNavItem("character_route", Icons.Default.Person, "Character")
    data object Phrase : BottomNavItem("phrase_route", Icons.Default.Edit, "Phrase")
    data object Password : BottomNavItem("password_route", Icons.Default.Lock, "Password")
}