package com.ausoft.quicknotes.data.repositories

import android.util.Log
import com.ausoft.quicknotes.data.datasources.RemoteDataSource
import com.ausoft.quicknotes.data.mappers.toNoteEntity
import com.ausoft.quicknotes.data.mappers.toNoteModel
import com.ausoft.quicknotes.domain.models.NoteModel
import com.ausoft.quicknotes.domain.repositories.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : NoteRepository {
    override suspend fun addNote(note: NoteModel) : Result<Unit> {
        return runCatching {
            remoteDataSource.addNote(note.toNoteEntity())
        }
    }

    override suspend fun getNotes(): Result<List<NoteModel>> {
        return runCatching {
            remoteDataSource.getNotes().map {
                it.toNoteModel()
            }
        }
    }

    override suspend fun deleteNote(note: NoteModel): Result<Unit> {
        return runCatching {
            remoteDataSource.deleteNote(note.toNoteEntity())
        }.onFailure { throwable ->
            Log.e("TAG", "Error deleting note: ${throwable.message}")
        }
    }
}