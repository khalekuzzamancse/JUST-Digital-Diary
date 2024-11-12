package navigation.hall.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp

/**
 * @param mealId used for additional query such as details, it not visible to user
 */
data class OrderMeal(
    val mealId: String,
    val symbolAvatar: String = "",
    val mealName: String,
    val studentId: String,
    val date: String,
    val hasTaken: Boolean,
    val type: TimeType
)

@Composable
fun OrderMealByStudent(
    modifier: Modifier = Modifier,
) {
    val types = listOf(
        TimeType.BreakFast, TimeType.Launch, TimeType.Dinner
    )
    val model = OrderMeal(
        mealId = "morning",
        mealName = "Morning",
        studentId = "140132",
        date = "12-12-12",
        symbolAvatar = "",
        hasTaken = false,
        type = TimeType.Launch
    )
    val ordered by remember {
        mutableStateOf(List(20) { index: Int ->
            val type = types[index % 3]
            model.copy(
                mealId = index.toString(),
                mealName = type.label,
                type = type
            )
        }
            .mapIndexed { index, item -> if (index % 2 == 0) item.copy(hasTaken = true) else item })
    }

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(minSize = 180.dp),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        items(ordered) { model ->
            OrderedMealItem(
                model = model,
            )
            Spacer(Modifier.height(8.dp))
        }
    }


}


@Composable
fun OrderedMealScreen(
    modifier: Modifier = Modifier,
    onProfileRequest: (id: String) -> Unit
) {
    val types = listOf(
        TimeType.BreakFast, TimeType.Launch, TimeType.Dinner
    )
    val highLighter = remember { SearcherHighlightedText() }
    val model = OrderMeal(
        mealId = "morning",
        mealName = "Morning",
        studentId = "140132",
        date = "12-12-12",
        symbolAvatar = "",
        hasTaken = false,
        type = TimeType.Launch
    )
    var ordered by remember {
        mutableStateOf(List(20) { index: Int ->
            model.copy(
                mealId = index.toString(),
                type = types[index % 3],
                mealName = types[index % 3].label
            )
        }
            .mapIndexed { index, item -> if (index % 2 == 0) item.copy(hasTaken = true) else item })
    }


    val lazyGridState = rememberLazyGridState()


    // val background = remember { ThemeInfo.LOGO_GREEN }////Right now supporting only light mode in both dark and light theme
//    val contentColor = remember {
//        if (background.luminance() > 0.5f) Color.Black else Color.White
//    }

    SearchView(
        onExitRequest = {},
        items = ordered,
        filterPredicate = { item, queryText ->
            val filter =
                item.studentId.contains(queryText, ignoreCase = true) || item.mealName.contains(
                    queryText,
                    ignoreCase = true
                )
            filter
        },
        content = { filteredResult, queryText ->
            LazyVerticalGrid(
                modifier = Modifier,
                columns = GridCells.Adaptive(minSize = 200.dp),
                state = lazyGridState,
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(filteredResult) { model ->
                    OrderedMealItem(
                        modifier = Modifier.gradientBackground(),
                        onProfileRequest = { onProfileRequest(model.studentId) },
                        model = model,
                        mealName = highLighter.getHighLightedString(model.mealName, queryText),
                        studentId = highLighter.getHighLightedString(model.studentId, queryText),
                        onCheckChanged = { checked ->
                            ordered = ordered.map {
                                if (model.mealId == it.mealId)
                                    model.copy(hasTaken = checked)
                                else it
                            }
                        }
                    )
                }
            }

        })


}

@Composable
fun OrderedMealLayoutStrategy(
    modifier: Modifier = Modifier,
    symbolAvatar: @Composable (Modifier) -> Unit,
    name: @Composable (Modifier) -> Unit,
    date: @Composable (Modifier) -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        symbolAvatar(Modifier.size(64.dp).clip(RoundedCornerShape(4.dp)))
        Spacer(modifier = Modifier.height(8.dp))
        name(Modifier.align(Alignment.Start))
        date(Modifier.align(Alignment.Start))
    }


}

@Composable
fun OrderedMealItem(
    modifier: Modifier = Modifier,
    model: OrderMeal,
) {
    val symbolBackground = model.type.cardBackground
    val contentColor =
        Color.White //as par the gradient background,color is white regardless of theme
    OrderedMealLayoutStrategy(
        modifier = modifier.gradientBackground(),
        symbolAvatar = {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .background(color = symbolBackground, shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                when (model.type) {
                    TimeType.BreakFast -> MorningIcon(
                        sunColor = model.type.symbolColor,
                        modifier = Modifier.padding(8.dp).size(64.dp)
                    )

                    TimeType.Launch -> NoonIcon(
                        modifier = Modifier.padding(8.dp).size(64.dp),
                        sunColor = model.type.symbolColor,
                        rayColor = model.type.symbolColor
                    )

                    TimeType.Dinner -> NightIcon(
                        modifier = Modifier.padding(8.dp).size(64.dp),
                        moonColor = model.type.symbolColor,
                        starColor = Color.Red,
                        background = model.type.cardBackground,
                    )
                }
            }
        },
        name = { mod ->
            Row(
                modifier = mod,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Timer,
                    contentDescription = "name",
                    tint = contentColor,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    modifier = mod,
                    text = model.mealName,
                    color = contentColor,
                    style = MaterialTheme.typography.titleMedium
                )
            }

        },
        date = { mod ->

            Row(
                modifier = mod,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.DateRange,
                    contentDescription = "name",
                    tint = contentColor,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    modifier = mod, text = model.date,
                    style = MaterialTheme.typography.titleMedium,
                    color = contentColor
                )
            }

        },
    )
}

/**
 * @param studentId taking separately as AnnotatedString for highlight while search
 * @param mealName taking separately as AnnotatedString for highlight while search
 */
@Composable
fun OrderedMealItem(
    modifier: Modifier = Modifier,
    model: OrderMeal,
    mealName: AnnotatedString,
    studentId: AnnotatedString,
    onProfileRequest: () -> Unit,
    onCheckChanged: (Boolean) -> Unit
) {
    val background = model.type.cardBackground
    val contentColor = Color.White
    val iconSize = remember { 30.dp }
    Surface(
        modifier = Modifier,
        shadowElevation = 2.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        OrderedMealLayoutStrategy(
            modifier = modifier.padding(8.dp),
            symbolAvatar = {
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .background(color = background, shape = RoundedCornerShape(8.dp))
                        .padding(4.dp)
                ) {
                    when (model.type) {
                        TimeType.BreakFast -> MorningIcon(
                            sunColor = model.type.symbolColor,
                            modifier = Modifier.padding(8.dp).size(iconSize)
                        )

                        TimeType.Launch -> NoonIcon(
                            modifier = Modifier.padding(8.dp).size(iconSize),
                            sunColor = model.type.symbolColor,
                            rayColor = model.type.symbolColor
                        )

                        TimeType.Dinner -> NightIcon(
                            modifier = Modifier.padding(8.dp).size(iconSize),
                            moonColor = model.type.symbolColor,
                            starColor = Color.Red,
                            background = model.type.cardBackground,
                        )
                    }
                }
            },
            name = { mod ->
                Text(
                    modifier = mod,
                    text = mealName,
                    color = contentColor,
                    style = MaterialTheme.typography.titleMedium
                )
            },
            date = { mod ->
                Text(
                    modifier = mod, text = model.date,
                    style = MaterialTheme.typography.labelSmall,
                    color = contentColor,
                )
            },
            studentId = { mod ->
                Text(
                    modifier = mod.clickable {
                        onProfileRequest()
                    },
                    text = studentId,
                    color = contentColor,
                    style = MaterialTheme.typography.titleMedium
                )
            },
            control = { mod ->
                Checkbox(
                    modifier = mod,
                    checked = model.hasTaken,
                    onCheckedChange = onCheckChanged,
                    colors = CheckboxDefaults.colors().copy(
                        uncheckedBorderColor = contentColor,
                        checkedCheckmarkColor = contentColor,
                        checkedBorderColor = contentColor,
                        uncheckedBoxColor = contentColor
                    )
                )
            }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OrderedMealLayoutStrategy(
    modifier: Modifier = Modifier,
    symbolAvatar: @Composable (Modifier) -> Unit,
    name: @Composable (Modifier) -> Unit,
    studentId: @Composable (Modifier) -> Unit,
    date: @Composable (Modifier) -> Unit,
    control: @Composable (Modifier) -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        symbolAvatar(Modifier.size(40.dp).clip(CircleShape))
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier) {
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                name(Modifier)
                date(Modifier.align(Alignment.CenterVertically))
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                studentId(Modifier)
                Spacer(modifier = Modifier.weight(1f, fill = true))
                control(Modifier)
            }

        }
    }

}