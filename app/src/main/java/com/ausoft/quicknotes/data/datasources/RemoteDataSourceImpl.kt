package com.ausoft.quicknotes.data.datasources

import com.ausoft.quicknotes.data.entities.NoteEntity
import com.ausoft.quicknotes.data.mappers.toMap
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : RemoteDataSource {

    override suspend fun addNote(note: NoteEntity) {
        firestore.collection("notes").add(note.toMap()).await()
    }

    override suspend fun getNotes(): List<NoteEntity> {
        val snapshot = firestore.collection("notes")
            .get()
            .await()
        return snapshot.documents.map { document ->
            NoteEntity(
                id = document.id,
                title = document.getString("title"),
                content = document.getString("content")
            )
        }
    }
}