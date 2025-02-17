package com.ausoft.quicknotes.domain.repositories

import com.ausoft.quicknotes.domain.models.NoteModel

interface NoteRepository {
    suspend fun addNote(note: NoteModel): Result<Unit>
    suspend fun getNotes(): Result<List<NoteModel>>
    suspend fun deleteNote(note: NoteModel): Result<Unit>
    suspend fun editNote(note: NoteModel): Result<Unit>
}