package com.ausoft.quicknotes.presentation.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun InputTextField(
    modifier: Modifier,
    label: String = "",
    text: String,
    enabled: Boolean = true,
    onTextChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        enabled = enabled,
        onValueChange = { onTextChange(it) },
        label = { Text(label) }
    )
}