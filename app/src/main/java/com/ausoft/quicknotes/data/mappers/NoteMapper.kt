package com.ausoft.quicknotes.data.mappers

import com.ausoft.quicknotes.data.entities.NoteEntity
import com.ausoft.quicknotes.domain.models.NoteModel

fun NoteEntity.toNoteModel(): NoteModel {
    return NoteModel(
        id = id,
        title = title,
        content = content
    )
}

fun NoteModel.toNoteEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        content = content
    )
}

fun NoteEntity.toMap(): Map<String, Any?> {
    return mapOf(
        "title" to title,
        "content" to content
    )
}