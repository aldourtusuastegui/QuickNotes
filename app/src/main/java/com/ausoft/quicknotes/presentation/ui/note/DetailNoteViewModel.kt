package com.ausoft.quicknotes.presentation.ui.note

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ausoft.quicknotes.domain.models.NoteModel
import com.ausoft.quicknotes.domain.usecases.DeleteNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailNoteViewModel @Inject constructor(
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailNoteUiState())
    val uiState = _uiState.asStateFlow()

    fun setNoteDetails(noteModel: NoteModel) {
        _uiState.update {
            it.copy(noteModel = noteModel)
        }
    }

    fun deleteNote() {
        viewModelScope.launch(dispatcher) {
            deleteNoteUseCase(_uiState.value.noteModel).onSuccess {
                _uiState.update {
                    Log.d("epale","success")
                    it.copy(wasSuccessfullyDeleted = true)
                }
            }.onFailure {
                _uiState.update {
                    Log.d("epale","failure")
                    it.copy(wasSuccessfullyDeleted = false)
                }
            }
        }
    }

}

data class DetailNoteUiState(
    val noteModel: NoteModel = NoteModel(),
    val wasSuccessfullyDeleted: Boolean = false
)