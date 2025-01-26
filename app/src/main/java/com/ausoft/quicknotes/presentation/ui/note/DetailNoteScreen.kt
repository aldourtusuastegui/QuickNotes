package com.ausoft.quicknotes.presentation.ui.note

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ausoft.quicknotes.R
import com.ausoft.quicknotes.domain.models.NoteModel
import com.ausoft.quicknotes.presentation.components.BottomDialog
import com.ausoft.quicknotes.presentation.components.InputTextField
import com.ausoft.quicknotes.presentation.components.TitleText
import com.ausoft.quicknotes.presentation.components.buttons.PrimaryButton
import com.ausoft.quicknotes.presentation.components.buttons.TertiaryButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailNoteScreen(
    modifier: Modifier,
    detailNoteViewModel: DetailNoteViewModel = hiltViewModel(),
    noteModel: NoteModel,
    onBackClick: () -> Unit,
) {
    val uiState = detailNoteViewModel.uiState.collectAsStateWithLifecycle().value
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(noteModel) {
       detailNoteViewModel.setNoteDetails(noteModel)
    }

    LaunchedEffect(uiState.wasSuccessfullyDeleted) {
        if (uiState.wasSuccessfullyDeleted) {
            onBackClick()
        }
    }

    BackHandler {
        onBackClick()
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
            text = uiState.noteModel.title.orEmpty(),
            enabled = false
        ) {

        }

        InputTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = uiState.noteModel.content.orEmpty(),
            enabled = false
        ) {

        }

        TertiaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            title = stringResource(R.string.edit_note).uppercase(),
            isEnabled = true
        ) {

        }

        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            title = stringResource(R.string.delete_note).uppercase(),
            isEnabled = true
        ) {
            showBottomSheet = true
        }

        if (showBottomSheet) {
            BottomDialog(
                sheetState = sheetState,
                title = stringResource(R.string.are_you_sure),
                primaryButtonTitle = stringResource(R.string.delete).uppercase(),
                secondaryButtonTitle = stringResource(R.string.cancel).uppercase(),
                onPrimary = {
                    showBottomSheet = false
                    detailNoteViewModel.deleteNote()
                },
                onSecondary = {
                    showBottomSheet = false
                },
                onDismiss = {
                    showBottomSheet = false
                }
            )
        }
    }
}