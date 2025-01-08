package com.ausoft.quicknotes.presentation.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ausoft.quicknotes.presentation.NoteViewModel

@Composable
fun NotesScreen(
    modifier: Modifier,
    viewModel: NoteViewModel = hiltViewModel()
){
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Button(onClick = {
            viewModel.addNote()
        }) {
            Text(text = "Add Note")
        }
    }
}