package com.ausoft.quicknotes.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ausoft.quicknotes.domain.models.NoteModel
import com.ausoft.quicknotes.presentation.ui.note.AddNoteScreen
import com.ausoft.quicknotes.presentation.ui.note.DetailNoteScreen
import com.ausoft.quicknotes.presentation.ui.note.NotesScreen

@Composable
fun AppNavigation(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(navController = navController, startDestination = Screen.AddNote.route) {
        composable(Screen.AddNote.route) {
            AddNoteScreen(modifier = Modifier.padding(innerPadding))
        }
        composable(Screen.Notes.route) {
            NotesScreen(modifier = Modifier.padding(innerPadding)) { note ->
                navController.navigate(Screen.DetailNote.createRoute(note))
            }
        }

        composable(
            route = "detail_note/{noteId}/{title}/{content}",
            arguments = listOf(
                navArgument("noteId") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType },
                navArgument("content") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")
            val title = backStackEntry.arguments?.getString("title")
            val content = backStackEntry.arguments?.getString("content")
            val noteModel = NoteModel(
                id = noteId,
                title = title,
                content = content
            )
            DetailNoteScreen(modifier = Modifier.padding(innerPadding), noteModel = noteModel) {
                navController.popBackStack()
            }
        }
    }
}