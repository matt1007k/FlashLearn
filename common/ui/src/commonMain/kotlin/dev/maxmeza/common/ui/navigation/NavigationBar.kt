package dev.maxmeza.common.ui.navigation

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NavigationBar(
    selectedKey: NavKey,
    onSelectKey: (NavKey) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        modifier = modifier
    ) {
        TOP_LEVEL_DESTINATIONS.forEach { (key, item) ->
            NavigationBarItem(
                selected = key == selectedKey,
                onClick = {
                    onSelectKey(key)
                },
                icon = {
                    Icon(painter = painterResource(item.icon), contentDescription = "Icon ${item.title}")
                },
                label = {
                    Text(text = stringResource(item.title))
                }
            )
        }
    }
}