package com.ausoft.quicknotes.presentation.ui.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ausoft.quicknotes.domain.models.NoteModel
import com.ausoft.quicknotes.domain.usecases.EditNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val editNoteUseCase: EditNoteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditNoteUiState())
    val uiState = _uiState.asStateFlow()

    fun setNoteDetails(noteModel: NoteModel) {
        _uiState.update {
            it.copy(noteModel = noteModel)
        }
    }

    fun onTitleChange(title: String) {
        _uiState.value = _uiState.value.copy(
            noteModel = NoteModel(
                id =   _uiState.value.noteModel.id,
                title = title,
                content = _uiState.value.noteModel.content
            )
        )
    }

    fun onContentChange(content: String) {
        _uiState.value = _uiState.value.copy(
            noteModel = NoteModel(
                id = _uiState.value.noteModel.id,
                title = _uiState.value.noteModel.title,
                content = content
            )
        )
    }

    fun validateForm() {
        val title = _uiState.value.noteModel.title
        val content = _uiState.value.noteModel.content
        if (!title.isNullOrEmpty() && !content.isNullOrEmpty()) {
            _uiState.value = _uiState.value.copy(isEditButtonEnabled = true)
        } else {
            _uiState.value = _uiState.value.copy(isEditButtonEnabled = false)
        }
    }

    fun editNote(note: NoteModel) {
        viewModelScope.launch(dispatcher) {
            editNoteUseCase(note).onSuccess {
                _uiState.update {
                    it.copy(
                        wasSuccessfullyEdited = true
                    )
                }
            }.onFailure {
                _uiState.update {
                    it.copy(
                        wasSuccessfullyEdited = false
                    )
                }
            }
        }
    }

}

data class EditNoteUiState(
    val noteModel: NoteModel = NoteModel(),
    val wasSuccessfullyEdited: Boolean = false,
    val isEditButtonEnabled: Boolean = false
)
