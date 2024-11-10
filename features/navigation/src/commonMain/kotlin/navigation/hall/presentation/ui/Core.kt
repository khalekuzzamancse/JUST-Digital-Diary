package navigation.hall.presentation.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

/**
 * - Symbol may be sun for day such as morning and noon and moon for night
 */
sealed interface TimeType {
    val symbolColor: Color
    val symbolBackground: Color
    val cardBackground: Color
    val label: String

    data object BreakFast : TimeType {

        override val symbolColor = Color(0xFFFFA07A)//sun
        override val symbolBackground = Color(0xFFFFD700)//ray
        override val cardBackground = Color(0xFFB0E2FF)//sky
        override val label = "BreakFast"
    }

    data object Launch : TimeType {


        override val symbolColor = Color(0xFFFFD700) //sun
        override val symbolBackground = Color(0xFFFFE082) //ray color
        override val cardBackground = Color(0xFFFFFF70) //sky color
        override val label = "Launch"
    }

    data object Dinner : TimeType {


        override val symbolColor = Color(0xFFFFD27F)
        override val symbolBackground = Color(0xFF1C1F4A)
        override val cardBackground = Color(0xFF1C1F4A)
        override val label = "Dinner"
    }
}
@Composable
fun MorningIcon(
    modifier: Modifier = Modifier,
    sunColor: Color = Color(0xFFFFDB00)
) {
    Canvas(modifier) {
        val center = size / 2f
        val radius = size.minDimension / 4f

        // Draw the half-circle sun
        drawArc(
            color = sunColor,
            startAngle = -180f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = Offset(center.width - radius, center.height - radius / 2),
            size = Size(radius * 2, radius)
        )

        // Draw sun rays - only above the sun
        val rayLength = radius * 1.5f
        val rayWidth = 4f
        val angles = listOf(-345f, -300f, -255f, -205f)

        for (angle in angles) {
            val radian = Math.toRadians(angle.toDouble()).toFloat()
            val startX = center.width + radius * cos(radian)
            val startY = center.height - radius * sin(radian)
            val endX = center.width + rayLength * cos(radian)
            val endY = center.height - rayLength * sin(radian)

            drawLine(
                color = sunColor, // Use the parameter color for the rays as well
                start = Offset(startX, startY),
                end = Offset(endX, endY),
                strokeWidth = rayWidth
            )
        }
    }


}


@Composable
fun NightIcon(
    modifier: Modifier = Modifier,
    moonColor: Color,
    starColor: Color,
    background: Color,
) {
    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val center = Offset(canvasWidth / 2f, canvasHeight / 2f)
        val outerRadius = size.minDimension / 4f

        drawCircle(
            color = moonColor,
            radius = outerRadius,
            center = center
        )

        // Adjust the offset to tilt the crescent moon slightly upward
        val offsetX = outerRadius / 3.5f
        val offsetY = outerRadius / 5f

        // Draw the inner circle to create the tilted crescent effect
        drawCircle(
            color = background,
            radius = outerRadius * 0.9f,
            center = Offset(center.x + offsetX, center.y - offsetY)
        )

        // Define maximum bounds for stars
        val padding = 5f
        val starMaxX = canvasWidth - padding
        val starMaxY = canvasHeight - padding

        // Draw stars within the canvas bounds
        drawStar(
            position = Offset(
                (center.x - 10f).coerceIn(padding, starMaxX),
                (center.y - 10f).coerceIn(padding, starMaxY)
            ),
            size = 6f,
            color = starColor
        )
        drawStar(
            position = Offset(
                (center.x + 15f).coerceIn(padding, starMaxX),
                (center.y - 8f).coerceIn(padding, starMaxY)
            ),
            size = 5f,
            color = starColor
        )
        drawStar(
            position = Offset(
                (center.x - 15f).coerceIn(padding, starMaxX),
                (center.y + 12f).coerceIn(padding, starMaxY)
            ),
            size = 4f,
            color = starColor
        )
    }
}

// Function to draw a star at a given position
fun DrawScope.drawStar(position: Offset, size: Float, color: Color) {
    val starPoints = getStarPoints(size, position)

    // Draw the star using a path
    drawPath(
        path = Path().apply {
            moveTo(starPoints[0].x, starPoints[0].y)
            for (i in 1 until starPoints.size) {
                lineTo(starPoints[i].x, starPoints[i].y)
            }
            close()
        },
        color = color
    )
}

// Function to generate the star's points
fun getStarPoints(size: Float, position: Offset): List<Offset> {
    val angle = Math.toRadians(36.0) // 36 degrees for a 5-pointed star
    val outerRadius = size
    val innerRadius = size / 2.5f
    val centerX = position.x
    val centerY = position.y

    val points = mutableListOf<Offset>()
    for (i in 0 until 10) {
        val radius = if (i % 2 == 0) outerRadius else innerRadius
        val x = centerX + (radius * cos(angle * i)).toFloat()
        val y = centerY - (radius * sin(angle * i)).toFloat()
        points.add(Offset(x, y))
    }
    return points
}


@Composable
fun NoonIcon(
    modifier: Modifier = Modifier,
    sunColor: Color,
    rayColor: Color
) {
    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val center = Offset(canvasWidth / 2f, canvasHeight / 2f)
        val radius = size.minDimension / 4f
        drawCircle(
            color = sunColor,
            radius = radius,
            center = center
        )

        val rayLength = radius * 2.5f
        val rayWidth = 4f
        val rayGap = radius * 0.2f
        val numRays = 8
        val angleStep = 360f / numRays


        for (i in 0 until numRays) {
            val angle = Math.toRadians((i * angleStep).toDouble()).toFloat()
            // Adjust the starting point of the rays to create a gap
            val startX = center.x + (radius + rayGap) * cos(angle)
            val startY = center.y + (radius + rayGap) * sin(angle)
            val endX = center.x + rayLength * cos(angle)
            val endY = center.y + rayLength * sin(angle)

            drawLine(
                color = rayColor,
                start = Offset(startX, startY),
                end = Offset(endX, endY),
                strokeWidth = rayWidth
            )
        }
    }
}

@Composable
fun CollapsibleToolbar() {
    TopAppbarM3_01()
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppbarM3_01() {

    var moveAppbarVerticallyBy by remember {
        mutableStateOf(0)
    }
    val density= LocalDensity.current
    val collapsableToolbarState = remember {
        CollapsableToolbarState(density) { y ->
            moveAppbarVerticallyBy = y
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(collapsableToolbarState.nestedScrollConnection),
        topBar = {
            CollapseAbleTopAppbar(
                moveAppbarVerticallyBy,
            )
        }) {
        LazyColumn {
            items(100) { index ->
                Text(
                    "I'm item $index", modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }

    }


}




class CollapsableToolbarState(
    density: Density,
    private val onToolbarOffsetChanged: (Int) -> Unit,
) {
    private val toolbarHeight: Dp = 48.dp
    private var toolbarHeightPx: Float
    var toolbarOffsetHeightPx = 0f
        private set(value) {
            field = value
            onToolbarOffsetChanged(value.roundToInt()) // Call the callback whenever the value changes
        }

    init {
        toolbarHeightPx = with(density) {
            toolbarHeight.roundToPx().toFloat()
        }
    }

    val nestedScrollConnection: NestedScrollConnection
        get() = object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                println("ONPreScroll")
                val delta = available.y
                val newOffset = toolbarOffsetHeightPx + delta
                toolbarOffsetHeightPx = newOffset.coerceIn(-toolbarHeightPx, 0f)
                return Offset.Zero
            }
        }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollapseAbleTopAppbar(
    moveAppbarVerticallyBy: Int = 0,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
) {


    TopAppBar(
        modifier = Modifier
            .offset { IntOffset(x = 0, y = moveAppbarVerticallyBy) },
        title = {
            Text("Home Page")
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
        }, scrollBehavior = scrollBehavior
    )
}


@Composable
fun NestedHorizontalScroller(
    modifier: Modifier=Modifier,
    header: @Composable (() -> Unit)? = null,
    children: List<@Composable () -> Unit>,
    height: Dp? = null,
    gap: Dp = 8.dp,
    showIndicator: Boolean = false
) {
    Column(
        modifier = if (height != null) modifier.height(height).fillMaxWidth() else modifier.wrapContentHeight().fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(gap)
    ) {
        header?.invoke()

        header?.let {
            Spacer(modifier = Modifier.height(gap))
        }

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            children.forEach { child -> child() }
        }
        if (showIndicator){
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(children.size) {
                    Box(modifier = Modifier.size(6.dp).background(color = Color.Black, shape = CircleShape))
                    Spacer(Modifier.width(4.dp))
                }
            }
        }


    }
}


fun Modifier.customShadow(
    maxWidth: Dp = Dp.Infinity,
    maxHeight: Dp = Dp.Infinity,
    minWidth: Dp = 0.dp,
    minHeight: Dp = 0.dp,
    backgroundColor: Color = Color.White,
    shadowColor: Color = Color.Black,
    blurRadius: Dp = 6.dp,
    offset: Offset = Offset(0f, 3f),
    borderRadius: Dp = 8.dp,
    padding: Dp = 8.dp
): Modifier {
    return this
        .shadow(
            elevation = blurRadius,
            shape = RoundedCornerShape(borderRadius),
            ambientColor = shadowColor.copy(alpha = 0.1f),
            spotColor = shadowColor.copy(alpha = 0.1f)
        )
        .clip(RoundedCornerShape(borderRadius))
        .background(backgroundColor)
        .padding(padding)
}


@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    onValueChange: (String) -> Unit,
    trailingIcon: (@Composable (Modifier) -> Unit)? = null
) {
    _BasicAuthTextField(
        modifier = modifier,
        enabled = enabled,
        label = label,
        value = value,
        visualTransformation = visualTransformation,
        leadingIcon = leadingIcon,
        keyboardType = keyboardType,
        onValueChanged = onValueChange,
        readOnly = readOnly,
        trailingIcon = trailingIcon
    )
}

@Composable
private fun _BasicAuthTextField(
    modifier: Modifier,
    label: String,
    value: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: ImageVector?,
    keyboardType: KeyboardType,
    onValueChanged: (String) -> Unit,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    trailingIcon: (@Composable (Modifier) -> Unit)? = null
) {

    val borderColor = if (enabled) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)

    val placeholderColor = if (enabled) MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
    else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)

    val textColor = if (enabled) MaterialTheme.colorScheme.onSurface
    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)

    val iconTint = if (enabled) MaterialTheme.colorScheme.tertiary
    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)

    val fontSize = 15.sp

    BasicTextField(
        enabled = enabled,
        value = value,
        onValueChange = onValueChanged,
        textStyle = TextStyle(fontSize = fontSize, color = textColor),
        singleLine = true,
        readOnly = readOnly,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        cursorBrush = if (enabled) SolidColor(MaterialTheme.colorScheme.primary) else SolidColor(Color.Transparent), // Hide cursor when disabled
        decorationBox = { innerText ->
            Row(
                modifier
                    .border(width = 0.dp, color = Color.Gray, shape = CircleShape)
                    .padding(vertical = 8.dp, horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    Icon(
                        imageVector = leadingIcon,
                        tint = iconTint,
                        contentDescription = "leading icon",
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .size(22.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                }

                Box(Modifier.weight(1f)) {
                    if (value.isEmpty()) {
                        _Placeholder(label, fontSize, placeholderColor)
                    }
                    // Call innerText in both cases to ensure the cursor is shown (if enabled)
                    innerText()
                }

                if (trailingIcon != null) {
                    Spacer(Modifier.width(8.dp))
                    trailingIcon(Modifier.padding(end = 8.dp))
                }
            }
        }
    )
}

@Composable
fun _Placeholder(text: String, fontSize: TextUnit, placeholderColor: Color) {
    Text(
        text = text,
        fontSize = fontSize,
        color = placeholderColor
    )
}


@Composable
private fun _Placeholder(
    text: String,
    fontSize: TextUnit,
    color: Color,
    fontWeight: FontWeight = FontWeight.W600,
) {
    Text(
        text = text,
        fontSize = fontSize,
        color = color,
        fontWeight = fontWeight
    )

}
