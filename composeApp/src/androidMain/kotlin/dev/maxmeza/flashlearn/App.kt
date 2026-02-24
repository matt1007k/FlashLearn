package dev.maxmeza.flashlearn

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.maxmeza.common.ui.theme.AppTheme
import dev.maxmeza.flashlearn.navigation.NavigationRoot

@Composable
@Preview()
fun App() {
    AppTheme {
        NavigationRoot()
    }
}

