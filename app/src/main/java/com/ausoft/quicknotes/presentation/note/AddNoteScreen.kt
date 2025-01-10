package com.ausoft.quicknotes.presentation.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ausoft.quicknotes.presentation.NoteViewModel
import com.ausoft.quicknotes.presentation.components.FilledButton
import com.ausoft.quicknotes.presentation.components.InputTextField

@Composable
fun AddNoteScreen(
    modifier: Modifier,
    viewModel: NoteViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {

        InputTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = "Title",
            text = uiState.value.title
        ) {
            viewModel.onTitleChange(it)
        }

        InputTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = "Content",
            text = uiState.value.content
        ) {
            viewModel.onContentChange(it)
        }

        FilledButton(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp, start = 16.dp, end = 16.dp),
            title = "ADD NOTE"
        ) {
            viewModel.addNote()
        }
    }
}