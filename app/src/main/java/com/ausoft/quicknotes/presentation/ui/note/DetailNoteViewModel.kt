package com.ausoft.quicknotes.presentation.ui.note

import androidx.lifecycle.ViewModel
import com.ausoft.quicknotes.domain.models.NoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailNoteViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(DetailNoteUiState())
    val uiState = _uiState.asStateFlow()

    fun setNoteDetails(noteModel: NoteModel) {
        _uiState.update {
            it.copy(noteModel = noteModel)
        }
    }

}

data class DetailNoteUiState(
    val noteModel: NoteModel = NoteModel()
)