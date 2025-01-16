package com.ausoft.quicknotes.data.mappers

import com.ausoft.quicknotes.data.entities.NoteEntity
import com.ausoft.quicknotes.domain.models.NoteModel

fun NoteEntity.toNoteModel(): NoteModel {
    return NoteModel(
        title = title,
        content = content
    )
}

fun NoteModel.toNoteEntity(): NoteEntity {
    return NoteEntity(
        title = title,
        content = content
    )
}