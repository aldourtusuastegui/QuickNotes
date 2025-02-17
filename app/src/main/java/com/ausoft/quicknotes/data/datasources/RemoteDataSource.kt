package com.ausoft.quicknotes.data.datasources

import com.ausoft.quicknotes.data.entities.NoteEntity

interface RemoteDataSource {
    suspend fun addNote(note: NoteEntity)
    suspend fun getNotes(): List<NoteEntity>
    suspend fun deleteNote(note: NoteEntity)
    suspend fun editNote(note: NoteEntity)
}