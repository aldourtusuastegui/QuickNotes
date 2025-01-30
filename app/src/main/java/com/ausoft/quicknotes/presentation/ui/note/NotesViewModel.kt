package com.ausoft.quicknotes.presentation.ui.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ausoft.quicknotes.domain.models.NoteModel
import com.ausoft.quicknotes.domain.usecases.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotesUiState())
    val uiState: StateFlow<NotesUiState> = _uiState.asStateFlow()

    init {
        getNotes()
    }

    fun getNotes() {
        _uiState.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            getNotesUseCase().onSuccess { notes ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        notes = notes
                    )
                }
            }
            .onFailure {
                _uiState.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }
        }
    }

    fun removeNoteById(noteId: String) {
        if (noteId.isNotEmpty()) {
            _uiState.update {
                it.copy(isLoading = true)
            }
            val notes = _uiState.value.notes.toMutableList()
            notes.removeIf { it.id == noteId }
            _uiState.update {
                it.copy(
                    isLoading = false,
                    notes = notes
                )
            }
        }
    }
}

data class NotesUiState(
    val isLoading: Boolean = false,
    val notes: List<NoteModel> = emptyList()
)