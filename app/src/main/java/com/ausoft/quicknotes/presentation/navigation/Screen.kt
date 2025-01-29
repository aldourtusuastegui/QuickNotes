package com.ausoft.quicknotes.presentation.navigation

import com.ausoft.quicknotes.domain.models.NoteModel

sealed class Screen(val route: String) {
    data object AddNote : Screen("add_note")
    data object Notes : Screen("notes")
    data class DetailNote(val noteId: String) : Screen("detail_note/{noteId}/{title}/{content}") {
        companion object {
            fun createRoute(
                noteModel: NoteModel
            ) = "detail_note/${noteModel.id}/${noteModel.title}/${noteModel.content}"
        }
    }
    data class EditNote(val noteId: String) : Screen("edit_note/{noteId}/{title}/{content}") {
        companion object {
            fun createRoute(
                noteModel: NoteModel
            ) = "edit_note/${noteModel.id}/${noteModel.title}/${noteModel.content}"
        }
    }
}