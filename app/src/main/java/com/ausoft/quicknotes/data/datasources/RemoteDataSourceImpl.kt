package com.ausoft.quicknotes.data.datasources

import com.ausoft.quicknotes.data.entities.NoteEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : RemoteDataSource {
    override suspend fun addNote(note: NoteEntity) {
        firestore.collection("notes").add(note).await()
    }
}