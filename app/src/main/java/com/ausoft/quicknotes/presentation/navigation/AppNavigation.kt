package com.ausoft.quicknotes.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ausoft.quicknotes.presentation.ui.note.AddNoteScreen

@Composable
fun AppNavigation(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(navController = navController, startDestination = "AddNote") {
        composable("AddNote") {
            AddNoteScreen(modifier = Modifier.padding(innerPadding))
        }
        composable("Notes") {
            Text("text2")
        }
        composable("minimal") {
            Text("text3")
        }
    }
}