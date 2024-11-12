package navigation.hall.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class MealCountModel(
    val label: String,
    val count: Int
)

interface MealSummaryController {
    val mealCountModels: StateFlow<List<MealCountModel>>
    fun load()
}

class MealSummaryControllerImpl : MealSummaryController {
    private val _models = MutableStateFlow(
        listOf(
            MealCountModel(
                label = "BreakFast",
                count = 100,
            ),
            MealCountModel(
                label = "Launch",
                count = 80,
            ), MealCountModel(
                label = "Dinner",
                count = 120,
            )
        )
    )
    override val mealCountModels = _models.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        load()
    }


    override fun load() {
        scope.launch {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryScreen() {
    // TODO: Refactor this to use a ViewModel to hold the controller and manage state
    val controller = MealSummaryControllerImpl()
    val models by controller.mealCountModels.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Meal Summary",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W500,
                            fontSize = 16.sp
                        )
                    )
                },
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(
                                    243,
                                    243,
                                    253
                                ), // Start with the extracted background color
                                Color(
                                    243,
                                    243,
                                    253
                                ).copy(alpha = 0f) // Fade to transparent at the top right
                            ),
                            start = Offset(
                                0f,
                                Float.POSITIVE_INFINITY
                            ), // Start from the bottom left
                            end = Offset(Float.POSITIVE_INFINITY, 0f) // End at the top right
                        )
                    )
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center
            ) {
                // TODO: Refactor this to use a ViewModel to hold the controller

                Bars(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    costs = models
                )


            }
        }
    )
}


@Composable
fun Bars(
    modifier: Modifier = Modifier,
    costs: List<MealCountModel>,
) {
    Column(
        modifier = modifier.padding(16.dp).horizontalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        BarChart(
            counts = costs
        )
    }
}


@Composable
fun BarChart(
    counts: List<MealCountModel>
) {
    val maxCost = counts.maxOfOrNull { it.count } ?: 0

    Row(
        modifier = Modifier.wrapContentWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        counts.forEach { cost ->
            Bar(
                model = cost,
                maxCount = maxCost,
                barColor = Color(0xFF008A4E),// Green Form JUST logo
                count = cost
            )
        }
    }
}

@Composable
fun Bar(
    model: MealCountModel,
    maxCount: Int,
    count: MealCountModel,
    barColor: Color = Color(0xFF7F00FF)
) {
    val barHeight = (150 * model.count / maxCount).coerceAtLeast(0)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier.width(IntrinsicSize.Min) // Ensure the width is based on the widest child
    ) {
        Count(count = count.count.toString())
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .height(barHeight.dp)
                .fillMaxWidth() // Ensures the bar width matches the text width
                .background(
                    color = barColor,
                    shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                )
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
                    clip = false
                )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Display cost below the bar with currency symbol
        Text(
            text = model.label,
            color = Color.Black,
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 16.sp),
            modifier = Modifier.padding(8.dp)
        )
    }
}


@Composable
fun Count(
    count: String,
    circleColor: Color = Color(0xFFDB372B),// Red from JUST logo
    countColor: Color = Color.White
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(IntrinsicSize.Max)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .customShadow(
                    backgroundColor = circleColor,
                    blurRadius = 8.dp,
                    shadowColor = Color.Black.copy(alpha = 0.1f),
                    borderRadius = 50.dp,
                    padding = 0.dp
                )
                .size(60.dp)


        ) {
            Text(
                text = count,
                style = TextStyle(
                    color = countColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}

