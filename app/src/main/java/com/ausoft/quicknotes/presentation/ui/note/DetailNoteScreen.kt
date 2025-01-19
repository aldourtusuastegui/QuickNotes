package com.ausoft.quicknotes.presentation.ui.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ausoft.quicknotes.R
import com.ausoft.quicknotes.domain.models.NoteModel
import com.ausoft.quicknotes.presentation.components.FilledButton
import com.ausoft.quicknotes.presentation.components.InputTextField
import com.ausoft.quicknotes.presentation.components.TitleText

@Composable
fun DetailNoteScreen(modifier: Modifier, noteModel: NoteModel) {
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
            text = noteModel.title.orEmpty(),
            enabled = false
        ) {

        }

        InputTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = noteModel.content.orEmpty(),
            enabled = false
        ) {

        }

        FilledButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            title = stringResource(R.string.edit_note),
            isEnabled = true
        ) {

        }

        FilledButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            title = stringResource(R.string.delete_note),
            isEnabled = true
        ) {

        }
    }
}