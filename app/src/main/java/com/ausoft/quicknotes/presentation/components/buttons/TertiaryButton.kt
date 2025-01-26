package com.ausoft.quicknotes.presentation.components.buttons

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun TertiaryButton(
    modifier: Modifier = Modifier,
    title: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier,
        enabled = isEnabled,
        colors = ButtonDefaults.textButtonColors(
            contentColor = Color.Black
        ),
        onClick = onClick
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge.copy(
                textDecoration = TextDecoration.Underline
            )
        )
    }
}