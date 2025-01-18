package com.ausoft.quicknotes.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ausoft.quicknotes.presentation.navigation.NavigationRoutes.AddNote
import com.ausoft.quicknotes.presentation.navigation.NavigationRoutes.Notes
import com.ausoft.quicknotes.presentation.ui.note.AddNoteScreen
import com.ausoft.quicknotes.presentation.ui.note.NotesScreen

@Composable
fun AppNavigation(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(navController = navController, startDestination = AddNote) {
        composable(AddNote) {
            AddNoteScreen(modifier = Modifier.padding(innerPadding))
        }
        composable(Notes) {
            NotesScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}