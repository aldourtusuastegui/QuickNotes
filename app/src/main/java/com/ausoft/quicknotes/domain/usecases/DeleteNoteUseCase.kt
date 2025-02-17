package com.ausoft.quicknotes.domain.usecases

import com.ausoft.quicknotes.domain.models.NoteModel
import com.ausoft.quicknotes.domain.repositories.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: NoteModel) = noteRepository.deleteNote(note)
}