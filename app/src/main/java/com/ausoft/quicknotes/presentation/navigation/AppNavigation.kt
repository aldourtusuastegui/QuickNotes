package com.ausoft.quicknotes.presentation.navigation

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ausoft.quicknotes.domain.models.NoteModel
import com.ausoft.quicknotes.presentation.ui.note.AddNoteScreen
import com.ausoft.quicknotes.presentation.ui.note.DetailNoteScreen
import com.ausoft.quicknotes.presentation.ui.note.EditNoteScreen
import com.ausoft.quicknotes.presentation.ui.note.NotesScreen

@Composable
fun AppNavigation(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(navController = navController, startDestination = Screen.AddNote.route) {
        composable(Screen.AddNote.route) {
            AddNoteScreen(modifier = Modifier.padding(innerPadding))
        }
        composable(Screen.Notes.route) { backStackEntry ->
            val noteDeletedFlow = backStackEntry.savedStateHandle.getStateFlow("noteId", "")
            val deletedNoteId by noteDeletedFlow.collectAsStateWithLifecycle()

            NotesScreen(
                modifier = Modifier.padding(innerPadding),
                removeNoteId = deletedNoteId,
                noteEdited = false,
                restartNoteId = {
                    backStackEntry.savedStateHandle["noteId"] = ""
                }
            ) { note ->
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
            var noteModel = NoteModel(
                id = noteId,
                title = title,
                content = content
            )

            val editNoteResult = navController.currentBackStackEntry
                ?.savedStateHandle
                ?.getStateFlow<Bundle?>("noteData", null)
                ?.collectAsStateWithLifecycle()

            val editedNoteId = editNoteResult?.value?.getString("noteId")
            val editedNoteTitle = editNoteResult?.value?.getString("noteTitle")
            val editedNoteContent = editNoteResult?.value?.getString("noteContent")

            if (editedNoteId != null && editedNoteTitle != null && editedNoteContent != null) {
                noteModel = NoteModel(
                    id = editedNoteId,
                    title = editedNoteTitle,
                    content = editedNoteContent
                )
            }

            DetailNoteScreen(
                modifier = Modifier.padding(innerPadding),
                noteModel = noteModel,
                onEdit = {
                    navController.navigate(Screen.EditNote.createRoute(noteModel))
                },
                onBack = {
                    navController.previousBackStackEntry?.savedStateHandle?.set("noteId", noteModel.id)
                    if (editedNoteId != null) {
                        navController.previousBackStackEntry?.savedStateHandle?.set("noteEdited", true)
                    }
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = "edit_note/{noteId}/{title}/{content}",
            arguments = listOf(
                navArgument("noteId") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType },
                navArgument("content") { type = NavType.StringType }
            )
        ) {
            val noteId = it.arguments?.getString("noteId")
            val title = it.arguments?.getString("title")
            val content = it.arguments?.getString("content")
            val noteModel = NoteModel(
                id = noteId,
                title = title,
                content = content
            )
            EditNoteScreen(
                modifier = Modifier.padding(innerPadding),
                noteModel = noteModel,
                onBack = {
                    navController.popBackStack()
                },
                onEdit = { note ->
                    val bundle = Bundle().apply {
                        putString("noteId", note.id)
                        putString("noteTitle", note.title)
                        putString("noteContent", note.content)
                    }
                    navController.previousBackStackEntry?.savedStateHandle?.set("noteData", bundle)
                    navController.popBackStack()
                }
            )
        }
    }
}