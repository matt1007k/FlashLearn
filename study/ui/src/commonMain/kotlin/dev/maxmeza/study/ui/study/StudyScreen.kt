package dev.maxmeza.study.ui.study

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.maxmeza.common.ui.resources.Drawable
import dev.maxmeza.common.ui.resources.Strings
import dev.maxmeza.common.ui.theme.AppTheme
import dev.maxmeza.common.ui.tts.speakText
import kotlin.math.abs
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


data class StudyCard(
    val word: String,
    val definition: String,
    val backSide: String = "",
    val pronunciation: String = "",
    val language: String = "",
    val languageCode: String = "",
    val learningTip: String = "Try using this word in a sentence today to help remember it better!"
)

private val sampleCards = listOf(
    StudyCard(
        word = "你好",
        definition = "Hello (informal)",
        backSide = "Common greeting in Mandarin Chinese",
        pronunciation = "nee-haow",
        language = "Chinese",
        languageCode = "zh"
    ),
    StudyCard(
        word = "谢谢",
        definition = "Thank you",
        backSide = "Way to say thanks in Mandarin",
        pronunciation = "syeh-syeh",
        language = "Chinese",
        languageCode = "zh"
    ),
    StudyCard(
        word = "再见",
        definition = "Goodbye",
        backSide = "Farewell expression in Mandarin",
        pronunciation = "dzai-jyen",
        language = "Chinese",
        languageCode = "zh"
    ),
    StudyCard(
        word = "老师",
        definition = "Teacher",
        backSide = "Respectful title for an educator",
        pronunciation = "lao-shrr",
        language = "Chinese",
        languageCode = "zh"
    ),
    StudyCard(
        word = "学生",
        definition = "Student",
        backSide = "A person who is studying",
        pronunciation = "shweh-sheng",
        language = "Chinese",
        languageCode = "zh"
    ),
    StudyCard(
        word = "中国",
        definition = "China",
        backSide = "Middle Kingdom - the country",
        pronunciation = "jong-gwo",
        language = "Chinese",
        languageCode = "zh"
    ),
    StudyCard(
        word = "안녕하세요",
        definition = "Hello (polite)",
        backSide = "Polite Korean greeting",
        pronunciation = "an-nyeong-ha-se-yo",
        language = "Korean",
        languageCode = "ko"
    ),
    StudyCard(
        word = "감사합니다",
        definition = "Thank you (formal)",
        backSide = "Formal way to thank in Korean",
        pronunciation = "gam-sa-ham-ni-da",
        language = "Korean",
        languageCode = "ko"
    ),
    StudyCard(
        word = "สวัสดี",
        definition = "Hello",
        backSide = "Thai greeting used anytime",
        pronunciation = "sa-wat-dee",
        language = "Thai",
        languageCode = "th"
    ),
    StudyCard(
        word = "ขอบคุณ",
        definition = "Thank you",
        backSide = "Thai expression of thanks",
        pronunciation = "kop-kun",
        language = "Thai",
        languageCode = "th"
    ),
    StudyCard(
        word = "Xin chào",
        definition = "Hello (formal)",
        backSide = "Vietnamese formal greeting",
        pronunciation = "sin chow",
        language = "Vietnamese",
        languageCode = "vi"
    ),
    StudyCard(
        word = "Cảm ơn",
        definition = "Thank you",
        backSide = "Vietnamese way to say thanks",
        pronunciation = "kahm uhn",
        language = "Vietnamese",
        languageCode = "vi"
    ),
    StudyCard(
        word = "Selamat pagi",
        definition = "Good morning",
        backSide = "Morning greeting in Malay/Indonesian",
        pronunciation = "seh-lah-mat pah-gee",
        language = "Malay",
        languageCode = "ms"
    ),
    StudyCard(
        word = "Terima kasih",
        definition = "Thank you",
        backSide = "Malay/Indonesian expression of gratitude",
        pronunciation = "teh-ree-mah kah-see",
        language = "Malay",
        languageCode = "ms"
    )
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyScreen(deckId: String, onBack: () -> Unit) {
    var currentCardIndex by remember { mutableIntStateOf(0) }
    var isAnswerRevealed by remember { mutableStateOf(false) }
    val cards = remember { sampleCards }
    val currentCard = cards[currentCardIndex]
    val progress = (currentCardIndex + 1).toFloat() / cards.size

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Rounded.ChevronLeft,
                            contentDescription = "Back"
                        )
                    }
                },
                title = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Spanish Vocabulary $deckId",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = stringResource(Strings.studyProgress, currentCardIndex + 1, cards.size),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = AppTheme.dimensions.defaultPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = Color(0xFFE85B81),
                trackColor = AppTheme.extraColors.progressTrack,
                strokeCap = StrokeCap.Round,
            )

            Spacer(modifier = Modifier.height(AppTheme.dimensions.largeMargin))

            FlashcardView(
                card = currentCard,
                isRevealed = isAnswerRevealed,
                onToggleReveal = { isAnswerRevealed = !isAnswerRevealed },
                onPlayTts = { speakText(currentCard.word, currentCard.languageCode) }
            )

            Spacer(modifier = Modifier.height(AppTheme.dimensions.largeMargin))

            AnimatedVisibility(
                visible = isAnswerRevealed,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    RatingButtons(
                        onHard = {
                            isAnswerRevealed = false
                            currentCardIndex = (currentCardIndex + 1).coerceAtMost(cards.size - 1)
                        },
                        onMedium = {
                            isAnswerRevealed = false
                            currentCardIndex = (currentCardIndex + 1).coerceAtMost(cards.size - 1)
                        },
                        onEasy = {
                            isAnswerRevealed = false
                            currentCardIndex = (currentCardIndex + 1).coerceAtMost(cards.size - 1)
                        }
                    )

                    Spacer(modifier = Modifier.height(AppTheme.dimensions.largeMargin))

                    LearningTipCard(tip = currentCard.learningTip)
                }
            }
        }
    }
}

@Composable
private fun FlashcardView(
    card: StudyCard,
    isRevealed: Boolean,
    onToggleReveal: () -> Unit,
    onPlayTts: () -> Unit
) {
    val rotation by animateFloatAsState(
        targetValue = if (isRevealed) 180f else 0f,
        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing),
        label = "card_flip"
    )
    val cameraDistance = with(androidx.compose.ui.platform.LocalDensity.current) { 12f * density }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                rotationY = rotation
                this.cameraDistance = cameraDistance
                scaleX = abs(kotlin.math.cos(Math.toRadians(rotation.toDouble()))).toFloat()
            }
            .clickable { onToggleReveal() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (rotation <= 90f) AppTheme.extraColors.flashcardBackground
            else AppTheme.extraColors.flashcardIconBackground
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        if (rotation <= 90f) {
            FlashcardFront(card = card, onPlayTts = onPlayTts)
        } else {
            FlashcardBack(card = card)
        }
    }
}

@Composable
private fun FlashcardFront(
    card: StudyCard,
    onPlayTts: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppTheme.dimensions.largeMargin),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(AppTheme.extraColors.flashcardIconBackground, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(Drawable.flash),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(AppTheme.dimensions.defaultPadding))

        Text(
            text = card.word,
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 36.sp),
            fontWeight = FontWeight.Bold,
            color = AppTheme.extraColors.textPrimary
        )

        if (card.pronunciation.isNotEmpty()) {
            Spacer(modifier = Modifier.height(AppTheme.dimensions.smallMargin))
            Text(
                text = stringResource(Strings.studyPronunciation, card.pronunciation),
                style = MaterialTheme.typography.bodyLarge.copy(fontStyle = FontStyle.Italic),
                color = AppTheme.extraColors.blue
            )
        }

        Spacer(modifier = Modifier.height(AppTheme.dimensions.largeMargin))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (card.language.isNotEmpty()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(AppTheme.extraColors.languageBadgeBackground, RoundedCornerShape(20.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(AppTheme.extraColors.red, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = card.languageCode.uppercase().take(2),
                            color = AppTheme.extraColors.white,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = card.language,
                        style = MaterialTheme.typography.labelMedium,
                        color = AppTheme.extraColors.textPrimary
                    )
                }
            }

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(AppTheme.extraColors.indigoLight, CircleShape)
                    .clickable { onPlayTts() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.PlayArrow,
                    contentDescription = "Play pronunciation",
                    tint = AppTheme.extraColors.white,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(AppTheme.dimensions.defaultPadding))
        Text(
            text = stringResource(Strings.studyTapToReveal),
            style = MaterialTheme.typography.labelMedium,
            color = AppTheme.extraColors.secondaryPrimary
        )

        Spacer(modifier = Modifier.height(AppTheme.dimensions.defaultPadding))

        StrokePracticeSection(word = card.word)
    }
}

@Composable
private fun FlashcardBack(card: StudyCard) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppTheme.dimensions.largeMargin)
            .graphicsLayer { scaleX = -1f },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(AppTheme.extraColors.flashcardIconBackground, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.Check,
                contentDescription = null,
                tint = AppTheme.extraColors.green,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.height(AppTheme.dimensions.defaultPadding))

        Text(
            text = card.definition,
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 28.sp),
            fontWeight = FontWeight.Bold,
            color = AppTheme.extraColors.textPrimary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(AppTheme.dimensions.smallMargin))

        Text(
            text = card.backSide,
            style = MaterialTheme.typography.bodyLarge,
            color = AppTheme.extraColors.secondaryPrimary,
            textAlign = TextAlign.Center
        )

        if (card.pronunciation.isNotEmpty()) {
            Spacer(modifier = Modifier.height(AppTheme.dimensions.smallMargin))
            Text(
                text = stringResource(Strings.studyPronunciation, card.pronunciation),
                style = MaterialTheme.typography.bodyLarge.copy(fontStyle = FontStyle.Italic),
                color = AppTheme.extraColors.blue
            )
        }
    }
}

@Composable
private fun StrokePracticeSection(word: String) {
    DrawingCanvas(word = word)
}

@Composable
private fun DrawingCanvas(word: String) {
    val paths = remember { mutableStateListOf<Path>() }
    var currentPath by remember { mutableStateOf<Path?>(null) }
    val textMeasurer = rememberTextMeasurer()
    val shadowColor = AppTheme.extraColors.canvasShadowText
    val strokeColor = AppTheme.extraColors.canvasStroke
    val canvasHeight = 100.dp

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(Strings.studyPracticeWrite),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Medium,
                color = AppTheme.extraColors.secondaryPrimary
            )
            TextButton(onClick = { paths.clear() }) {
                Text(
                    text = stringResource(Strings.studyClearCanvas),
                    style = MaterialTheme.typography.labelMedium,
                    color = AppTheme.extraColors.red
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(canvasHeight)
                .clip(RoundedCornerShape(12.dp))
                .background(AppTheme.extraColors.canvasBackground),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.foundation.Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        awaitEachGesture {
                            val down = awaitFirstDown(requireUnconsumed = false)
                            val path = Path().apply { moveTo(down.position.x, down.position.y) }
                            currentPath = path
                            while (true) {
                                val event = awaitPointerEvent()
                                when (event.type) {
                                    PointerEventType.Move -> {
                                        event.changes.forEach { change ->
                                            change.consume()
                                            path.lineTo(change.position.x, change.position.y)
                                        }
                                        currentPath = Path().apply { addPath(path) }
                                    }
                                    PointerEventType.Release -> {
                                        event.changes.forEach { it.consume() }
                                        paths.add(path)
                                        currentPath = null
                                        break
                                    }
                                }
                            }
                        }
                    }
            ) {
                val textLayoutResult = textMeasurer.measure(
                    text = word,
                    style = TextStyle(
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                val textSize = textLayoutResult.size
                val topLeft = Offset(
                    x = (size.width - textSize.width) / 2f,
                    y = (size.height - textSize.height) / 2f
                )
                drawText(textLayoutResult, topLeft = topLeft, color = shadowColor)

                val stroke = Stroke(
                    width = 6f,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )
                paths.forEach { path ->
                    drawPath(path, color = strokeColor, style = stroke)
                }
                currentPath?.let { path ->
                    drawPath(path, color = strokeColor, style = stroke)
                }
            }
        }
    }
}

@Composable
private fun RatingButtons(
    onHard: () -> Unit,
    onMedium: () -> Unit,
    onEasy: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.smallMargin)
    ) {
        RatingButton(
            modifier = Modifier.weight(1f),
            label = stringResource(Strings.studyHard),
            icon = Icons.Rounded.Close,
            containerColor = AppTheme.extraColors.ratingHardBackground,
            iconColor = AppTheme.extraColors.red,
            onClick = onHard
        )
        RatingButton(
            modifier = Modifier.weight(1f),
            label = stringResource(Strings.studyMedium),
            icon = null,
            containerColor = AppTheme.extraColors.ratingMediumBackground,
            iconColor = AppTheme.extraColors.orange,
            onClick = onMedium
        )
        RatingButton(
            modifier = Modifier.weight(1f),
            label = stringResource(Strings.studyEasy),
            icon = Icons.Rounded.Check,
            containerColor = AppTheme.extraColors.ratingEasyBackground,
            iconColor = AppTheme.extraColors.green,
            onClick = onEasy
        )
    }
}

@Composable
private fun RatingButton(
    modifier: Modifier = Modifier,
    label: String,
    icon: ImageVector?,
    containerColor: Color,
    iconColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(iconColor, CircleShape)
                )
            }
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Medium,
                color = AppTheme.extraColors.textPrimary
            )
        }
    }
}

@Composable
private fun LearningTipCard(tip: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = AppTheme.extraColors.learningTipBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimensions.defaultPadding),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.defaultPadding),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(AppTheme.extraColors.learningTipIconBackground, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(Drawable.dna),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(Strings.studyLearningTip),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = AppTheme.extraColors.textPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = tip,
                    style = MaterialTheme.typography.labelMedium,
                    color = AppTheme.extraColors.secondaryPrimary
                )
            }
        }
    }
}
