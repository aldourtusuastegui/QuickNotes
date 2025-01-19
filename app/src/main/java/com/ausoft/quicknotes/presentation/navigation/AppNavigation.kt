package com.ausoft.quicknotes.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ausoft.quicknotes.presentation.navigation.NavigationRoutes.ADD_NOTE
import com.ausoft.quicknotes.presentation.navigation.NavigationRoutes.NOTES
import com.ausoft.quicknotes.presentation.navigation.NavigationRoutes.DETAIL_NOTE
import com.ausoft.quicknotes.presentation.ui.note.AddNoteScreen
import com.ausoft.quicknotes.presentation.ui.note.DetailNoteScreen
import com.ausoft.quicknotes.presentation.ui.note.NotesScreen

@Composable
fun AppNavigation(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(navController = navController, startDestination = ADD_NOTE) {
        composable(ADD_NOTE) {
            AddNoteScreen(modifier = Modifier.padding(innerPadding))
        }
        composable(NOTES) {
            NotesScreen(modifier = Modifier.padding(innerPadding)) {
                navController.navigate(DETAIL_NOTE)
            }
        }
        composable(DETAIL_NOTE) {
            DetailNoteScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}