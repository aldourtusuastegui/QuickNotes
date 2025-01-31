package com.ausoft.quicknotes.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.ausoft.quicknotes.R
import com.ausoft.quicknotes.presentation.navigation.Screen

@Composable
fun BottomBarMaterial3(
    currentRoute: String?,
    onNavigateToAddNote: () -> Unit,
    onNavigateToNotes: () -> Unit
) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.primaryContainer) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = stringResource(R.string.add_note)) },
            label = { Text(stringResource(R.string.add_note)) },
            selected = currentRoute == Screen.AddNote.route,
            onClick = onNavigateToAddNote,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                unselectedIconColor = MaterialTheme.colorScheme.onSurface
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Settings, contentDescription = stringResource(R.string.notes)) },
            label = { Text(stringResource(R.string.notes)) },
            selected = currentRoute == Screen.Notes.route,
            onClick = onNavigateToNotes,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                unselectedIconColor = MaterialTheme.colorScheme.onSurface
            )
        )
    }
}