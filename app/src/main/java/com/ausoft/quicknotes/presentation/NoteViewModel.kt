package com.ausoft.quicknotes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ausoft.quicknotes.domain.models.NoteModel
import com.ausoft.quicknotes.domain.usecases.AddNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    fun addNote() {
        _uiState.value = _uiState.value.copy(isButtonEnabled = false)
        viewModelScope.launch(Dispatchers.IO) {
            addNoteUseCase(NoteModel(_uiState.value.title, _uiState.value.content)).onSuccess {
                _uiState.value = _uiState.value.copy(isButtonEnabled = true)
            }.onFailure {
                _uiState.value = _uiState.value.copy(isButtonEnabled = true)
            }
        }
    }
}

data class NoteUiState(
    val isButtonEnabled: Boolean = true,
    val title: String = "",
    val content: String = "",
)