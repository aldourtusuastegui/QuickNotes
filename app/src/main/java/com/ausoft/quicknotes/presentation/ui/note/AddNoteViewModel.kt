package com.ausoft.quicknotes.presentation.ui.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ausoft.quicknotes.domain.models.NoteModel
import com.ausoft.quicknotes.domain.usecases.AddNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(NoteUiState())
    val uiState = _uiState.asStateFlow()

    fun onTitleChange(title: String) {
        _uiState.value = _uiState.value.copy(title = title)
    }

    fun onContentChange(content: String) {
        _uiState.value = _uiState.value.copy(content = content)
    }

    fun validateForm() {
        _uiState.value = _uiState.value.copy(isButtonEnabled = _uiState.value.title.isNotEmpty() && _uiState.value.content.isNotEmpty())
    }

    fun addNote() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update {
                it.copy(isButtonEnabled = false)
            }
            addNoteUseCase(
                NoteModel(
                    title = _uiState.value.title,
                    content = _uiState.value.content
                )
            ).onSuccess {
                _uiState.update {
                    it.copy(
                        isButtonEnabled = false,
                        title = "",
                        content = "",
                        wasSuccessfullyAdded = true
                    )
                }
            }.onFailure {
                _uiState.update {
                    it.copy(
                        isButtonEnabled = false,
                        title = "",
                        content = "",
                        wasSuccessfullyAdded = false
                    )
                }
            }
        }
    }
}

data class NoteUiState(
    val isButtonEnabled: Boolean = false,
    val title: String = "",
    val content: String = "",
    val wasSuccessfullyAdded: Boolean = false
)