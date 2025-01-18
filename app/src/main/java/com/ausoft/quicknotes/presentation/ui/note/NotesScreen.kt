package com.ausoft.quicknotes.presentation.ui.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ausoft.quicknotes.R
import com.ausoft.quicknotes.presentation.components.TitleText

@Composable
fun NotesScreen(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TitleText(modifier = Modifier.padding(start = 16.dp), title = stringResource(R.string.notes))


    }
}