package com.ausoft.quicknotes.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ausoft.quicknotes.presentation.ui.note.AddNoteScreen

@Composable
fun AppNavigation(innerPadding: PaddingValues) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            AddNoteScreen(modifier = Modifier.padding(innerPadding))
        }
        composable("details") {
            Text("text2")
        }
        composable("minimal") {
            Text("text3")
        }
    }
}