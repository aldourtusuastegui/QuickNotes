package com.ausoft.quicknotes.presentation.ui.note

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailNoteViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(DetailNoteUiState())
    val uiState = _uiState.asStateFlow()


}

data class DetailNoteUiState(
    val title: String = "",
    val content: String = ""
)