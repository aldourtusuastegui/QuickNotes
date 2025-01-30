package com.ausoft.quicknotes.presentation.navigation

import android.os.Bundle
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
import com.ausoft.quicknotes.presentation.utils.Constants.EDITED_NOTE_CONTENT
import com.ausoft.quicknotes.presentation.utils.Constants.EDITED_NOTE_ID
import com.ausoft.quicknotes.presentation.utils.Constants.EDITED_NOTE_TITLE
import com.ausoft.quicknotes.presentation.utils.Constants.EMPTY_STRING
import com.ausoft.quicknotes.presentation.utils.Constants.NOTE_CONTENT
import com.ausoft.quicknotes.presentation.utils.Constants.NOTE_DATA
import com.ausoft.quicknotes.presentation.utils.Constants.NOTE_ID
import com.ausoft.quicknotes.presentation.utils.Constants.NOTE_TITLE

@Composable
fun AppNavigation(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(navController = navController, startDestination = Screen.AddNote.route) {

        //AddNoteScreen
        composable(Screen.AddNote.route) {
            AddNoteScreen(modifier = Modifier.padding(innerPadding))
        }

        //List Of Notes
        composable(Screen.Notes.route) { backStackEntry ->
            val noteDeletedFlow = backStackEntry.savedStateHandle.getStateFlow(NOTE_ID, EMPTY_STRING)
            val deletedNoteId by noteDeletedFlow.collectAsStateWithLifecycle()

            NotesScreen(
                modifier = Modifier.padding(innerPadding),
                removeNoteId = deletedNoteId,
                restartNoteId = {
                    backStackEntry.savedStateHandle[NOTE_ID] = EMPTY_STRING
                }
            ) { note ->
                navController.navigate(Screen.DetailNote.createRoute(note))
            }
        }

        //Detail Of Notes
        composable(
            route = "detail_note/{noteId}/{noteTitle}/{noteContent}",
            arguments = listOf(
                navArgument(NOTE_ID) { type = NavType.StringType },
                navArgument(NOTE_TITLE) { type = NavType.StringType },
                navArgument(NOTE_CONTENT) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString(NOTE_ID)
            val title = backStackEntry.arguments?.getString(NOTE_TITLE)
            val content = backStackEntry.arguments?.getString(NOTE_CONTENT)
            var noteModel = NoteModel(
                id = noteId,
                title = title,
                content = content
            )

            val editNoteResult = navController.currentBackStackEntry
                ?.savedStateHandle
                ?.getStateFlow<Bundle?>(NOTE_DATA, null)
                ?.collectAsStateWithLifecycle()

            val editedNoteId = editNoteResult?.value?.getString(EDITED_NOTE_ID)
            val editedNoteTitle = editNoteResult?.value?.getString(EDITED_NOTE_TITLE)
            val editedNoteContent = editNoteResult?.value?.getString(EDITED_NOTE_CONTENT)

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
                onDelete = {
                    navController.previousBackStackEntry?.savedStateHandle?.set(NOTE_ID, noteModel.id)
                    navController.popBackStack()
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        //Edit Note
        composable(
            route = "edit_note/{noteId}/{noteTitle}/{noteContent}",
            arguments = listOf(
                navArgument(NOTE_ID) { type = NavType.StringType },
                navArgument(NOTE_TITLE) { type = NavType.StringType },
                navArgument(NOTE_CONTENT) { type = NavType.StringType }
            )
        ) {
            val noteId = it.arguments?.getString(NOTE_ID)
            val title = it.arguments?.getString(NOTE_TITLE)
            val content = it.arguments?.getString(NOTE_CONTENT)
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
                        putString(EDITED_NOTE_ID, note.id)
                        putString(EDITED_NOTE_TITLE, note.title)
                        putString(EDITED_NOTE_CONTENT, note.content)
                    }
                    navController.previousBackStackEntry?.savedStateHandle?.set(NOTE_DATA, bundle)
                    navController.popBackStack()
                }
            )
        }
    }
}