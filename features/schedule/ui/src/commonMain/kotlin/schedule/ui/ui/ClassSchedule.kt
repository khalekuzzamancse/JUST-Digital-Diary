@file:Suppress("FunctionName", "ComposableNaming")

package schedule.ui.ui

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import schedule.di.DomainModuleFactory
import schedule.ui.ui.common.DepartmentSessionHeader
import schedule.ui.ui.common.TextSizeMeasurer
import schedule.ui.model.ClassDetailModel
import schedule.ui.model.ClassScheduleModel
import schedule.ui.model.DailyClassScheduleModel
import schedule.ui.presenter.ClassSchedulePresenter


/**
 * TODO: Need to Refactor, use a Viewmodel
 */
@Composable
fun ClassScheduleScreen() {
    var classSchedule by remember { mutableStateOf<ClassScheduleModel?>(null) }
    LaunchedEffect(Unit) {
        val result = DomainModuleFactory.createRetrieveClassScheduleUseCase().execute("")
        result.onSuccess {
            try {
                classSchedule =
                    ClassSchedulePresenter().fromDomainToUIModel(result.getOrThrow().first())

            } catch (e: Exception) {

            }

        }

    }
    classSchedule?.let { schedule ->
        ClassSchedule(schedule)
    }

}
@Composable
fun ClassSchedule(
    classSchedule: ClassScheduleModel
) {
    val inCompleteInfo = classSchedule.routine.isEmpty()
    if (inCompleteInfo) {
        Text("Schedule is empty")
    } else {
        Surface(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .verticalScroll(rememberScrollState())
                .padding(8.dp),
            shadowElevation = 3.dp
        ) {
            Column {
                DepartmentSessionHeader(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    departmentName = classSchedule.dept,
                    session = classSchedule.session,
                    year = classSchedule.year,
                    semester = classSchedule.semester,
                )

                _ClassSchedule(
                    schedule = classSchedule.routine
                )
            }

        }

    }


}
/**
 * - Every Cell has same size, cell height=Max Of all text height,width=max of all text width
 */
@Composable
fun _ClassSchedule(schedule: List<DailyClassScheduleModel>) {


    val measurer = rememberTextMeasurer()
    val density = LocalDensity.current
    val style = MaterialTheme.typography.bodySmall
    val verticalGap = 8.dp
    val textMeasurer = remember(schedule) { TextSizeMeasurer(measurer, style, density) }

    val courseMaxWidth =
        remember(schedule) { textMeasurer.calculateWidthPerHour(schedule.flatMap { it.items }) }

    val courseMaxHeight =
        remember(schedule) { textMeasurer.calculateMaxHeight(schedule.flatMap { it.items }) }
    val dayLabelWidth =
        remember(schedule) { textMeasurer.calculateMaxWidth(schedule.map { it.day }) }

    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        _DayColumn(
            modifier = Modifier,
            labelWidth = dayLabelWidth,
            labelHeight = courseMaxHeight,
            labelVerticalGap = verticalGap,
            style = style,
            days = schedule.map { it.day }
        )

        Spacer(Modifier.width(verticalGap))
        //shown all the content(except day)
        Column(
            Modifier,
        ) {
            schedule.forEach {
                _EachRow(
                    daySchedule = it,
                    widthPerHour = courseMaxWidth,
                    height = courseMaxHeight, style
                )
                Spacer(Modifier.height(verticalGap))
            }

        }
    }


}

@Composable
private fun _EachRow(
    daySchedule: DailyClassScheduleModel,
    widthPerHour: Dp,
    height: Dp,
    style: TextStyle
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {

        daySchedule
            .items
            .filter { it.allFieldNotBlank() }  //string by empty either for editing or other reason so pass only valid data for safe rendering
            .forEach { routineItem ->
                _RoutineItemBox(
                    modifier = Modifier
                        .width(widthPerHour * routineItem.durationInHours)
                        .height(height)
                        .background(MaterialTheme.colorScheme.tertiaryContainer),
                    routineItem = routineItem,
                    style = style
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
    labelVerticalGap: Dp,
    style: TextStyle,
    days: List<String>,
) {
    Column(
        modifier = Modifier.width(labelWidth)
    ) {
        days.forEach { day ->
            _DayLabel(
                modifier = Modifier
                    .height(labelHeight)
                    .width(labelWidth)
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                day = day,
                style = style
            )
            Spacer(Modifier.height(labelVerticalGap))
        }
    }

}

@Composable
private fun _DayLabel(
    modifier: Modifier = Modifier,
    day: String,
    style: TextStyle
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day,
            modifier = Modifier,
            style = style
        )
    }
}

@Composable
private fun _RoutineItemBox(
    modifier: Modifier = Modifier,
    routineItem: ClassDetailModel,
    style: TextStyle,

    ) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = routineItem.courseCode, style = style)
            Text(text = routineItem.time, style = style)
            Text(text = routineItem.teacherShortName, style = style)
        }
    }
}



