package com.ausoft.quicknotes.presentation.ui.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ausoft.quicknotes.R
import com.ausoft.quicknotes.domain.models.NoteModel
import com.ausoft.quicknotes.presentation.components.InputTextField
import com.ausoft.quicknotes.presentation.components.TitleText
import com.ausoft.quicknotes.presentation.components.buttons.AlertButton
import com.ausoft.quicknotes.presentation.components.buttons.DangerButton

@Composable
fun DetailNoteScreen(
    modifier: Modifier,
    noteModel: NoteModel,
    detailNoteViewModel: DetailNoteViewModel = hiltViewModel()
) {
    val uiState = detailNoteViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(noteModel) {
       detailNoteViewModel.setNoteDetails(noteModel)
    }

    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)) {
        TitleText(
            modifier = Modifier.padding(start = 16.dp),
            title = stringResource(R.string.detail_note)
        )

        InputTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = uiState.value.noteModel.title.orEmpty(),
            enabled = false
        ) {

        }

        InputTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = uiState.value.noteModel.content.orEmpty(),
            enabled = false
        ) {

        }

        AlertButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            title = stringResource(R.string.edit_note),
            isEnabled = true
        ) {

        }

        DangerButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            title = stringResource(R.string.delete_note),
            isEnabled = true
        ) {

        }
    }
}