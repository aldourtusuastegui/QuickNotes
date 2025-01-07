package com.ausoft.quicknotes.data.repositories

import com.ausoft.quicknotes.data.datasources.RemoteDataSource
import com.ausoft.quicknotes.data.mappers.toNoteEntity
import com.ausoft.quicknotes.domain.models.NoteModel
import com.ausoft.quicknotes.domain.repositories.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : NoteRepository {
    override suspend fun addNote(note: NoteModel) {
        remoteDataSource.addNote(note.toNoteEntity())
    }
}