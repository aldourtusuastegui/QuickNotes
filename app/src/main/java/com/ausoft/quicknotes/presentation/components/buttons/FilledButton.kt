package com.ausoft.quicknotes.presentation.components.buttons

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FilledButton(
    modifier: Modifier,
    title: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(modifier = modifier, enabled = isEnabled, onClick = onClick) {
        Text(text = title)
    }
}