package com.ausoft.quicknotes.presentation.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun InputTextField(
    modifier: Modifier,
    label: String,
    text: String,
    onTextChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = { onTextChange(it) },
        label = { Text(label) }
    )
}