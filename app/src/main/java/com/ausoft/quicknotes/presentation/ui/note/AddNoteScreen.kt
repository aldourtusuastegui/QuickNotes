package com.ausoft.quicknotes.presentation.ui.note

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ausoft.quicknotes.R
import com.ausoft.quicknotes.presentation.components.buttons.PrimaryButton
import com.ausoft.quicknotes.presentation.components.InputTextField
import com.ausoft.quicknotes.presentation.components.TitleText

@Composable
fun AddNoteScreen(
    modifier: Modifier,
    viewModel: NoteViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TitleText(Modifier.padding(start = 16.dp), stringResource(R.string.add_note))

        InputTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = stringResource(R.string.title),
            text = uiState.value.title
        ) {
            viewModel.onTitleChange(it)
            viewModel.validateForm()
        }

        InputTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = stringResource(R.string.content),
            text = uiState.value.content
        ) {
            viewModel.onContentChange(it)
            viewModel.validateForm()
        }

        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            title = stringResource(R.string.add_note),
            isEnabled = uiState.value.isButtonEnabled
        ) {
            viewModel.addNote()
            focusManager.clearFocus()
            if (uiState.value.wasSuccessfullyAdded) {
                Toast.makeText(context, "Added note successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }
}