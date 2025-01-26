package com.ausoft.quicknotes.presentation.components.buttons

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    title: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        enabled = isEnabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
        containerColor = Color.Black,
        contentColor = Color.White
    ),
    ) {
        Text(text = title)
    }
}