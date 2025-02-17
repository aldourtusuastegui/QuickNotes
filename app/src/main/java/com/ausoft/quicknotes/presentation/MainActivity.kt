package com.ausoft.quicknotes.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ausoft.quicknotes.R
import com.ausoft.quicknotes.presentation.components.BottomBarMaterial3
import com.ausoft.quicknotes.presentation.components.SmallTopAppBar
import com.ausoft.quicknotes.presentation.navigation.AppNavigation
import com.ausoft.quicknotes.presentation.navigation.Screen
import com.ausoft.quicknotes.presentation.theme.QuickNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val currentBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = currentBackStackEntry?.destination?.route
            val isBottomNavigationVisible = currentRoute == Screen.AddNote.route || currentRoute == Screen.Notes.route

            QuickNotesTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    SmallTopAppBar(title = stringResource(R.string.app_name), isBackVisible = !isBottomNavigationVisible) {
                        navController.popBackStack()
                    }
                }, bottomBar = {
                    if (isBottomNavigationVisible) {
                        BottomBarMaterial3(
                            currentRoute = currentRoute,
                            onNavigateToAddNote = { navController.navigate(Screen.AddNote.route) },
                            onNavigateToNotes = { navController.navigate(Screen.Notes.route) }
                        )
                    }
                }) { innerPadding ->
                    AppNavigation(navController, innerPadding)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuickNotesTheme {
        Greeting("Android")
    }
}