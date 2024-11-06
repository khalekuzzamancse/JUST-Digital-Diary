package navigation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.sin
import kotlin.random.Random

@Composable
fun AnimateDrawerPreview() {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        val drawerState = rememberAnimatedDrawerState(
            drawerWidth = 280.dp,
            drawerMode = DrawerMode.SlideBehind,
        )
        val scope = rememberCoroutineScope()
        AnimatedDrawer(
            modifier = Modifier.fillMaxSize(),
            state = drawerState,
            drawerContent = {
                SettingsOptions(
                    modifier = Modifier.fillMaxSize(),
                    onCloseClick = {
                        scope.launch {
                            drawerState.close()
                        }
                    }
                )
            },
            background = {
                Box(Modifier.size(50.dp).background(Color.Red))
//                            AsyncImage(
//                                model = ImageRequest.Builder(LocalContext.current)
//                                    .data("https://placekitten.com/1200/1200")
//                                    .crossfade(true)
//                                    .build(),
//                                modifier = Modifier.fillMaxSize(),
//                                contentScale = ContentScale.Crop,
//                                contentDescription = null,
//                            )
            },
            content = {
                CatList(
                    modifier = Modifier.fillMaxSize(),
                    onOpenClick = {
                        scope.launch { drawerState.open() }
                    }
                )
            }
        )
    }

}


@Stable
interface AnimatedDrawerState {
    var density: Float
    val drawerWidth: Dp

    val drawerTranslationX: Float
    val drawerElevation: Float

    val backgroundTranslationX: Float
    val backgroundAlpha: Float

    val contentScaleX: Float
    val contentScaleY: Float
    val contentTranslationX: Float
    val contentTransformOrigin: TransformOrigin

    suspend fun open()
    suspend fun close()
}

private const val AnimationDurationMillis = 600
private const val DrawerMaxElevation = 8f

sealed interface DrawerMode {
    data class SlideRight(
        val drawerGap: Dp
    ) : DrawerMode

    object SlideBehind : DrawerMode
}

private val DrawerMode.scaleFactor: Float
    get() = when (this) {
        is DrawerMode.SlideRight -> .2f
        DrawerMode.SlideBehind -> .4f
    }

private fun DrawerMode.translationX(
    drawerWidth: Float,
    fraction: Float,
    density: Float,
) = when (this) {
    is DrawerMode.SlideRight -> (drawerWidth + drawerGap.value * density) * fraction
    DrawerMode.SlideBehind -> ((.6f * drawerWidth) * sin(fraction * PI)).toFloat()
}

private val DrawerMode.transformOrigin: TransformOrigin
    get() = when (this) {
        is DrawerMode.SlideRight -> TransformOrigin(pivotFractionX = 0f, pivotFractionY = .5f)
        DrawerMode.SlideBehind -> TransformOrigin(pivotFractionX = 1f, pivotFractionY = .5f)
    }

@Stable
class AnimatedDrawerStateImpl(
    override val drawerWidth: Dp,
    private val drawerMode: DrawerMode,
) : AnimatedDrawerState {

    private val animation = Animatable(0f)
    private val animationY = Animatable(0f)

    override var density by mutableStateOf(1f)

    override val drawerTranslationX: Float
        get() = -drawerWidth.value * density * (1f - animation.value)

    override val drawerElevation: Float
        get() = DrawerMaxElevation * animation.value

    override val backgroundTranslationX: Float
        get() = animation.value * drawerWidth.value * density

    override val backgroundAlpha: Float
        get() = .25f * animation.value

    override val contentScaleX: Float
        get() = 1f - drawerMode.scaleFactor * animation.value

    override val contentScaleY: Float
        get() = 1f - drawerMode.scaleFactor * animationY.value

    override val contentTranslationX: Float
        get() = drawerMode.translationX(
            drawerWidth = drawerWidth.value * density,
            fraction = animation.value,
            density = density,
        )

    override val contentTransformOrigin: TransformOrigin
        get() = drawerMode.transformOrigin

    override suspend fun open() {
        coroutineScope {
            launch {
                animation.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(durationMillis = AnimationDurationMillis)
                )
            }
            launch {
                animationY.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(
                        durationMillis = AnimationDurationMillis,
                        delayMillis = AnimationDurationMillis / 4,
                    ),
                )
            }
        }
    }

    override suspend fun close() {
        coroutineScope {
            launch {
                animation.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = AnimationDurationMillis)
                )
            }
            launch {
                animationY.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(
                        durationMillis = AnimationDurationMillis,
                        delayMillis = AnimationDurationMillis / 4,
                    ),
                )
            }
        }
    }
}

@Composable
fun rememberAnimatedDrawerState(
    drawerWidth: Dp,
    drawerMode: DrawerMode,
): AnimatedDrawerState = remember {
    AnimatedDrawerStateImpl(
        drawerWidth = drawerWidth,
        drawerMode = drawerMode,
    )
}

@Composable
fun AnimatedDrawer(
    modifier: Modifier = Modifier,
    state: AnimatedDrawerState = rememberAnimatedDrawerState(
        drawerWidth = 280.dp,
        DrawerMode.SlideRight(drawerGap = 16.dp),
    ),
    drawerContent: @Composable () -> Unit,
    background: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = {
            drawerContent()
            background()
            content()
        }
    ) { measurables, constraints ->
        state.density = density
        val drawerWidthPx = state.drawerWidth.value * density
        val (drawerContentMeasurable, backgroundMeasurable, contentMeasurable) = measurables
        val drawerContentConstraints = Constraints.fixed(
            width = drawerWidthPx.coerceAtMost(constraints.maxWidth.toFloat()).toInt(),
            height = constraints.maxHeight,
        )
        val drawerContentPlaceable = drawerContentMeasurable.measure(drawerContentConstraints)
        val contentConstraints = Constraints.fixed(
            width = constraints.maxWidth,
            height = constraints.maxHeight,
        )
        val contentPlaceable = contentMeasurable.measure(contentConstraints)
        val backgroundPlaceable = backgroundMeasurable.measure(
            Constraints.fixed(
                width = constraints.maxWidth,
                height = constraints.maxHeight,
            )
        )
        layout(
            width = constraints.maxWidth,
            height = constraints.maxHeight,
        ) {
            backgroundPlaceable.placeRelativeWithLayer(
                IntOffset.Zero
            ) {
                translationX = state.backgroundTranslationX
                alpha = state.backgroundAlpha
            }
            contentPlaceable.placeRelativeWithLayer(
                IntOffset.Zero,
            ) {
                transformOrigin = state.contentTransformOrigin
                scaleX = state.contentScaleX
                scaleY = state.contentScaleY
                translationX = state.contentTranslationX
            }
            drawerContentPlaceable.placeRelativeWithLayer(
                IntOffset.Zero,
            ) {
                translationX = state.drawerTranslationX
                shadowElevation = state.drawerElevation
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CatList(
    modifier: Modifier = Modifier,
    onOpenClick: () -> Unit,
) {
    val urls = remember {
        List(100) {
            val width = 400 + 20 * Random.nextInt(20)
            val height = 400 + 20 * Random.nextInt(20)
            "https://placekitten.com/$width/$height"
        }
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text("Drawer Sample")
                },
                navigationIcon = {
                    IconButton(onClick = onOpenClick) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null,
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(200.dp),
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(items = urls) { url ->
                CardImage(
                    url = url,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
fun CardImage(
    url: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        Box(Modifier.size(300.dp).background(Color.Magenta))
//        AsyncImage(
//            model = ImageRequest.Builder(LocalContext.current)
//                .data(url)
//                .crossfade(true)
//                .build(),
//            modifier = Modifier
//                .aspectRatio(1f)
//                .padding(all = 16.dp)
//                .clip(shape = RoundedCornerShape(8.dp)),
//            contentScale = ContentScale.Crop,
//            contentDescription = null,
//        )
    }
}

@Composable
private fun SettingsOptions(
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background,
    ) {
        val lorem = "Lorem ipsum dolor sit amet consectetur adipiscing elit"
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .weight(1f)
                        .padding(all = 16.dp),
                )
                IconButton(
                    onClick = onCloseClick,
                    modifier = Modifier
                        .padding(all = 16.dp)
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "close")
                }
            }
            lorem.split(" ").forEach { label ->
                DrawerEntry(
                    label = label,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { }
                        .padding(vertical = 16.dp),
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Composable
fun DrawerEntry(
    label: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = label,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier,
        textAlign = TextAlign.Center,
    )
}