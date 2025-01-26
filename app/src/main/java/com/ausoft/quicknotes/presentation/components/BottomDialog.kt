package com.ausoft.quicknotes.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ausoft.quicknotes.presentation.components.buttons.PrimaryButton
import com.ausoft.quicknotes.presentation.components.buttons.TertiaryButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomDialog(
    sheetState: SheetState,
    title: String,
    primaryButtonTitle: String,
    secondaryButtonTitle: String,
    onPrimary: () -> Unit,
    onSecondary: () -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleText(
                modifier = Modifier.padding(bottom = 16.dp),
                title = title,
                textAlign = TextAlign.Center
            )
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                title = primaryButtonTitle
            ) {
                onPrimary()
            }
            TertiaryButton(
                modifier = Modifier.fillMaxWidth(),
                title = secondaryButtonTitle
            ) {
                onSecondary()
            }
        }
    }
}