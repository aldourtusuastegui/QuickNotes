package com.ausoft.quicknotes.presentation.ui.note

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ausoft.quicknotes.R
import com.ausoft.quicknotes.domain.models.NoteModel
import com.ausoft.quicknotes.presentation.components.LoadingIndicator
import com.ausoft.quicknotes.presentation.components.NoteItem
import com.ausoft.quicknotes.presentation.components.TitleText

@Composable
fun NotesScreen(
    modifier: Modifier,
    notesViewModel: NotesViewModel = hiltViewModel(),
    removeNoteId: String,
    restartNoteId: () -> Unit,
    onNoteClick: (NoteModel) -> Unit
) {

    val uiState = notesViewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        notesViewModel.getNotes()
    }

    LaunchedEffect(removeNoteId) {
        restartNoteId()
        notesViewModel.removeNoteById(removeNoteId)
    }

    if (uiState.isLoading) {
        LoadingIndicator(modifier = Modifier.fillMaxSize())
    } else {
        if (uiState.notes.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text = stringResource(R.string.no_notes),
                    fontSize = 20.sp
                )
            }
        } else {
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
                            id = note.id.orEmpty(),
                            title = note.title.orEmpty(),
                            content = note.content.orEmpty(),
                            onNoteClick = onNoteClick
                        )
                    }
                }

            }
        }
    }
}