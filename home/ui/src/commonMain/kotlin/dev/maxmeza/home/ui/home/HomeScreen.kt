package dev.maxmeza.home.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.maxmeza.common.ui.resources.Drawable
import dev.maxmeza.common.ui.resources.Strings
import dev.maxmeza.common.ui.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onGoStudy: (deckId: String) -> Unit) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "My Decks", style = MaterialTheme.typography.titleMedium)
                },
                actions = {
                    IconButton(onClick = {}) {
                        Image(
                            painter = painterResource(resource = Drawable.search),
                            contentDescription = "Icon search",
                            colorFilter = ColorFilter.tint(AppTheme.extraColors.textPrimary)
                        )
                    }
                    IconButton(onClick = {}) {
                        Image(
                            painter = painterResource(Drawable.settings),
                            contentDescription = "Icon settings",
                            colorFilter = ColorFilter.tint(AppTheme.extraColors.textPrimary)
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.defaultPadding),
        ) {
            item {
                GoalCard()
            }
            stickyHeader {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = AppTheme.dimensions.defaultPadding,
                        )
                        .background(MaterialTheme.colorScheme.surface),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Recent Decks", fontWeight = FontWeight.SemiBold, color = AppTheme.extraColors.textPrimary)
                    TextButton(onClick = {}) {
                        Text("See all")
                    }
                }
            }
            items(10) {
                DeckItem(onGoStudy = { onGoStudy(it.toString())})
            }
        }
    }
}

@Composable
private fun DeckItem(onGoStudy: () -> Unit) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = AppTheme.dimensions.defaultPadding),
        onClick = onGoStudy,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.surfaceContainerHighest),
        shape = RoundedCornerShape(AppTheme.dimensions.defaultPadding)
    ) {
        Column(
            modifier = Modifier
                .padding(AppTheme.dimensions.defaultPadding),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.smallMargin)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Spanish Vocabulary", fontWeight = FontWeight.SemiBold, color = AppTheme.extraColors.textPrimary)
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.extraSmallMargin)
                    ) {
                        Text(text = "120 cards", style = MaterialTheme.typography.labelMedium)
                        Text(text = ".", style = MaterialTheme.typography.labelMedium)
                        Text(text = "15 due today", style = MaterialTheme.typography.labelMedium)
                    }
                }
                Box(
                    modifier = Modifier
                        .background(AppTheme.extraColors.yellowLight, shape = CircleShape)
                        .size(AppTheme.dimensions.sizeMedium),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Drawable.cube),
                        contentDescription = "Item icon",
                        tint = AppTheme.extraColors.yellow,
                    )
                }
            }
            val tags = listOf("Language", "Daily")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.smallMargin)
            ) {
                items(tags.size) { index ->
                    val tag = tags[index]
                    FilterChip(
                        true,
                        {},
                        { Text(
                            text = tag,
                            color = AppTheme.extraColors.textPrimary,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Medium
                        ) },
                        shape = CircleShape,
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun GoalCard() {
    Card(
        modifier = Modifier
            .padding(horizontal = AppTheme.dimensions.defaultPadding),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(all = AppTheme.dimensions.defaultPadding),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.extraSmallMargin)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        stringResource(Strings.homeGoalTitle),
                        color = AppTheme.extraColors.white,
                        fontSize = 10.sp
                    )
                    Text(
                        text = stringResource(Strings.homeGoalCardCount, 10, 's'),
                        color = AppTheme.extraColors.white,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Box(
                    modifier = Modifier
                        .size(AppTheme.dimensions.sizeLarge)
                        .background(AppTheme.extraColors.indigoLight, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painterResource(resource = Drawable.flash),
                        contentDescription = "Icon flash",
                        tint = AppTheme.extraColors.white
                    )
                }
            }

            LinearProgressIndicator(
                progress = { 0.2f },
                color = AppTheme.extraColors.white,
                trackColor = AppTheme.extraColors.indigoLight,
                strokeCap = StrokeCap.Round,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(AppTheme.dimensions.smallMargin)
            )
            Text(
                stringResource(Strings.homeGoalProgress, 10, 10, "s"),
                color = AppTheme.extraColors.white,
                fontSize = 10.sp
            )
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreviewDark() {
    AppTheme {
        HomeScreen(onGoStudy = {})
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreviewLight() {
    AppTheme(false) {
        HomeScreen(onGoStudy = {})
    }
}