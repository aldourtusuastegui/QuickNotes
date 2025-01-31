package com.ausoft.quicknotes.domain.usecases

import com.ausoft.quicknotes.domain.models.NoteModel
import com.ausoft.quicknotes.domain.repositories.NoteRepository
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke() : Result<List<NoteModel>> {
        return noteRepository.getNotes()
    }
}