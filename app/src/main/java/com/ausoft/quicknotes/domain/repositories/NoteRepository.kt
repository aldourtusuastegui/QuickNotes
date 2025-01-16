package com.ausoft.quicknotes.domain.repositories

import com.ausoft.quicknotes.domain.models.NoteModel

interface NoteRepository {
    suspend fun addNote(note: NoteModel): Result<Unit>
}