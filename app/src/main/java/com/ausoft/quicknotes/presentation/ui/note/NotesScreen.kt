package com.ausoft.quicknotes.presentation.ui.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ausoft.quicknotes.R
import com.ausoft.quicknotes.presentation.components.NoteItem
import com.ausoft.quicknotes.presentation.components.TitleText

@Composable
fun NotesScreen(
    modifier: Modifier,
    notesViewModel: NotesViewModel = hiltViewModel(),
    onNoteClick: () -> Unit
) {

    val uiState = notesViewModel.uiState.collectAsStateWithLifecycle().value

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TitleText(
            modifier = Modifier.padding(start = 16.dp),
            title = stringResource(R.string.notes)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            items(uiState.notes) { note ->
                NoteItem(
                    title = note.title.orEmpty(),
                    content = note.content.orEmpty(),
                    onNoteClick = onNoteClick
                )
            }
        }

    }
}