package com.ausoft.quicknotes.data.datasources

import com.ausoft.quicknotes.data.entities.NoteEntity

interface RemoteDataSource {
    suspend fun addNote(note: NoteEntity)
}