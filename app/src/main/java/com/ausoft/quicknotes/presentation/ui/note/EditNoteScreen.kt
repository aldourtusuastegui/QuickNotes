package com.ausoft.quicknotes.presentation.ui.note

import androidx.activity.compose.BackHandler
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
import com.ausoft.quicknotes.presentation.components.buttons.PrimaryButton

@Composable
fun EditNoteScreen(
    modifier: Modifier,
    editNoteViewModel: EditNoteViewModel = hiltViewModel(),
    noteModel: NoteModel,
    onBack: () -> Unit,
    onEdit: (noteId: String) -> Unit
) {
    val uiState = editNoteViewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect(uiState.wasSuccessfullyEdited) {
        if (uiState.wasSuccessfullyEdited) {
            uiState.noteModel.id?.let(onEdit)
        }
    }

    LaunchedEffect(noteModel) {
        editNoteViewModel.setNoteDetails(noteModel)
    }

    BackHandler {
        onBack()
    }

    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)) {
        TitleText(
            modifier = Modifier.padding(start = 16.dp),
            title = stringResource(R.string.edit_note)
        )

        InputTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = uiState.noteModel.title.orEmpty(),
        ) {
            editNoteViewModel.onTitleChange(it)
            editNoteViewModel.validateForm()
        }

        InputTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = uiState.noteModel.content.orEmpty(),
        ) {
            editNoteViewModel.onContentChange(it)
            editNoteViewModel.validateForm()
        }

        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            title = stringResource(R.string.save_changes).uppercase(),
            isEnabled = uiState.isEditButtonEnabled
        ) {
            editNoteViewModel.editNote(uiState.noteModel)
        }
    }

}