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

    fun addNote() {
        viewModelScope.launch(Dispatchers.IO) {
            addNoteUseCase(NoteModel("New Title","New Content"))
        }
    }

}

data class NoteUiState(
    val title: String = "",
    val content: String = "",
)