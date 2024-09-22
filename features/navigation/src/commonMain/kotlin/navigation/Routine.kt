@file:Suppress("FunctionName","ComposableNaming")
package navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class RoutineItem(
    val subject: String,
    val time: String,
    val room: String,
    val durationInHours: Int // Duration of the class in hours
)

// Data model for a day
data class DaySchedule(
    val day: String,
    val items: List<RoutineItem>
)

// List of sample data for each day (you can add more days and items)
val sampleDays = listOf(
    DaySchedule(
        day = "Sat",
        items = listOf(
            RoutineItem("Math", "9:00 AM - 10:00 AM", "Room 101", 1),
            RoutineItem("Physics", "10:00 AM - 12:00 PM", "Room 102", 2),
            RoutineItem("Chemistry", "1:00 PM - 2:00 PM", "Room 103", 1)
        )
    ),
    DaySchedule(
        day = "Sun",
        items = listOf(
            RoutineItem("Biology", "9:00 AM - 11:00 AM", "Room 201", 2),
            RoutineItem("English", "11:00 AM - 12:00 PM", "Room 202", 1)
        )
    ),
    DaySchedule(
        day = "Mon",
        items = listOf(
            RoutineItem("Math", "9:00 AM - 10:00 AM", "Room 101", 1),
            RoutineItem("Physics", "10:00 AM - 12:00 PM", "Room 102", 2),
            RoutineItem("History", "1:00 PM - 2:00 PM", "Room 103", 1)
        )
    ),
    DaySchedule(
        day = "Tue",
        items = listOf(
            RoutineItem("Chemistry", "9:00 AM - 10:00 AM", "Room 101", 1),
            RoutineItem("Biology", "10:00 AM - 12:00 PM", "Room 102", 2),
            RoutineItem("English", "1:00 PM - 2:00 PM", "Room 103", 1)
        )
    ),
    DaySchedule(
        day = "Wed",
        items = listOf(
            RoutineItem("History", "9:00 AM - 10:00 AM", "Room 201", 1),
     RoutineItem("Physics", "10:00 AM - 12:00 PM", "Room 202", 2),
            RoutineItem("Math", "1:00 PM - 2:00 PM", "Room 203", 1)
        )
    )
)

@Composable
fun RoutineApp() {

    val measurer = rememberTextMeasurer()
    val density = LocalDensity.current

    val widthPerHour = calculateWidthPerHour(
        courses = sampleDays.flatMap { it.items },
        MaterialTheme.typography.bodyMedium,
        measurer = measurer,
        density = density
    )

    val height = calculateMaxHeight(
        sampleDays.flatMap { it.items },
        style = MaterialTheme.typography.bodyMedium,
        measurer = measurer,
        density = density
    )

    val verticalGap=8.dp

    Row(  modifier = Modifier.padding(16.dp).fillMaxWidth()) {
       Column {
           _DayColumn(
               modifier = Modifier,
               labelWidth =50.dp,
               labelHeight = height,
               labelVerticalGap = 8.dp
           )
       }
        Spacer(Modifier.width(8.dp))
        Column(
            Modifier.horizontalScroll(rememberScrollState()),
        ) {
            sampleDays.forEach {
                _EachRow(
                    daySchedule = it,
                    widthPerHour = widthPerHour,
                    height = height
                )
                Spacer(Modifier.height(verticalGap))
            }

        }
    }


}

@Composable
fun _EachRow(daySchedule: DaySchedule, widthPerHour: Dp, height: Dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            daySchedule.items.forEach { routineItem ->
                _RoutineItemBox(
                    modifier = Modifier
                        .width(widthPerHour * routineItem.durationInHours)
                        .height(height)
                        .background(Color.Cyan),
                    routineItem = routineItem,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(Modifier.width(8.dp))
            }
        }

}


@Composable
private fun _DayColumn(
    modifier: Modifier = Modifier,
    labelHeight: Dp,
    labelWidth: Dp,
    labelVerticalGap:Dp,
) {
    Column(
        modifier = Modifier
            .width(labelWidth)
    ) {
        sampleDays.forEach { day ->
            _DayLabel(
                modifier = Modifier
                    .height(labelHeight)
                    .width(labelWidth)
                    .background(Color.LightGray),
                day = day.day
            )
            Spacer(Modifier.height(labelVerticalGap))
        }
    }

}

@Composable
private fun _DayLabel(
    modifier: Modifier = Modifier,
    day: String,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day,
            modifier = Modifier,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun _RoutineItemBox(
    modifier: Modifier = Modifier,
    routineItem: RoutineItem,
    style: TextStyle,

    ) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = routineItem.subject, style = style)
            Text(text = routineItem.time, style = style)
            Text(text = routineItem.room, style = style)
        }
    }
}


fun calculateWidthPerHour(
    courses: List<RoutineItem>,
    style: TextStyle,
    measurer: TextMeasurer,
    density: Density,
): Dp {
    val safePadding = 20.dp
    val maxMeasuredTextWidth = courses.map { it._routeItemWidth(measurer, style) }.max()
    val maxTextWidthInDp = with(density) { maxMeasuredTextWidth.toDp() }
    return maxTextWidthInDp + safePadding
}


private fun RoutineItem._routeItemWidth(measurer: TextMeasurer, style: TextStyle) =
    listOf(
        _measureWidth(this.time, measurer, style),
        _measureWidth(this.subject, measurer, style),
        _measureWidth(this.room, measurer, style),
    ).max()

private fun _measureWidth(text: String, measurer: TextMeasurer, style: TextStyle) =
    measurer.measure(text = text, style = style).size.width

@Composable
fun calculateMaxHeight(
    courses: List<RoutineItem>,
    measurer: TextMeasurer,
    style: TextStyle,
    density: Density,
): Dp {
    val safePadding = 20.dp
    val maxMeasuredTextHeight = courses.map { it._measureHeight(measurer, style) }.max()
    val maxTextHeightInDp = with(density) { maxMeasuredTextHeight.toDp() }
    return maxTextHeightInDp + safePadding
}

private fun _measureHeight(text: String, measurer: TextMeasurer, style: TextStyle) =
    measurer.measure(text = text, style = style).size.height

private fun RoutineItem._measureHeight(measurer: TextMeasurer, style: TextStyle) =
    _measureHeight(this.time, measurer, style) +
            _measureHeight(this.subject, measurer, style) +
            _measureHeight(this.room, measurer, style)
