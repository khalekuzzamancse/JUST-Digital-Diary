package schedule.ui.public_

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import common.ui.EmptyContentScreen
import schedule.di.DiContainer
import schedule.ui.core.SessionHeader
import schedule.ui.core.TextSizeMeasurer
import schedule.presentationlogic.model.ExamDetailsModel
import schedule.presentationlogic.model.ExamScheduleModel

/**
 * TODO: Need to Refactor, use a Viewmodel
 */

@Composable
fun ViewExamScheduleScreen() {
    var examSchedule by remember { mutableStateOf<ExamScheduleModel?>(null) }
    LaunchedEffect(Unit) {
        val result = DiContainer.readExaScheduleUseCase().execute("")
        result.onSuccess {
            try {

//                examSchedule = with(ModelMapper){result.getOrThrow().first().tom}
//                    ExamScheduleModelMapper().fromDomainToUIModel()

            } catch (_: Exception) {

            }

        }

    }
    examSchedule?.let { schedule ->
        ExamScheduleScreen(
            examSchedule = schedule,
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .verticalScroll(rememberScrollState())
        )
    }
    if(examSchedule==null){
        EmptyContentScreen(message = "No exam schedule found")
    }

}

@Composable
fun ExamScheduleScreen(
    modifier: Modifier = Modifier,
    examSchedule: ExamScheduleModel
) {
    if (examSchedule.exams.isEmpty()) {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text("No exam found")
        }

    } else {
        Surface(
            modifier = modifier
                .padding(8.dp),
            shadowElevation = 3.dp
        ) {
            Column {
                SessionHeader(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    departmentName = examSchedule.dept,
                    session = examSchedule.session,
                    year = examSchedule.year,
                    semester = examSchedule.semester,
                )
                _ExamRoutine(
                    modifier = Modifier
                        .padding(16.dp),
                    //avoid render empty item because text will be measured before layout
                    exams = examSchedule.exams.filter { it.allFieldAvailable() },
                )
            }


        }
    }


}


@Composable
private fun _ExamRoutine(
    modifier: Modifier = Modifier,
    exams: List<ExamDetailsModel>
) {

    val measurer = rememberTextMeasurer()
    val density = LocalDensity.current
    val style = MaterialTheme.typography.bodyMedium
    val verticalGap = 8.dp

    // Create a text measurer utility for columns
    val textMeasurer = remember(exams) { TextSizeMeasurer(measurer, style, density) }

    // Define column labels
    val labels = listOf("Exam Date", "Course Code", "Course Title", "Time")

    // Calculate max widths for each column
    val dateColumnWidth = _calculateColumnWidth(exams.map { it.date }, labels[0], textMeasurer)
    val courseCodeColumnMaxWidth =
        _calculateColumnWidth(exams.map { it.courseCode }, labels[1], textMeasurer)
    val courseTitleColumnMaxWidth =
        _calculateColumnWidth(listOf(labels[2]), labels[2], textMeasurer) + 100.dp
    val timeColumnMaxWidth =
        _calculateColumnWidth(exams.map { it.time }, labels[3], textMeasurer)

    // Render the exam schedule
    Column(
        modifier = modifier
    ) {
        _RenderHeaderRow(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.tertiary),
            labels = labels,
            columnWidths = listOf(
                dateColumnWidth,
                courseCodeColumnMaxWidth,
                courseTitleColumnMaxWidth,
                timeColumnMaxWidth
            ),
            style = style,
            verticalGap = verticalGap
        )

        Spacer(Modifier.height(verticalGap))

        exams.forEach { exam ->
            _RenderExamRow(
                modifier = Modifier.height(IntrinsicSize.Max),
                exam = exam,
                columnWidths = listOf(
                    dateColumnWidth,
                    courseCodeColumnMaxWidth,
                    courseTitleColumnMaxWidth,
                    timeColumnMaxWidth
                ),
                style = style,
                verticalGap = verticalGap
            )
            Spacer(Modifier.height(verticalGap))
        }
    }


}

// Helper function to calculate the maximum width of a column
@Composable
private fun _calculateColumnWidth(
    texts: List<String>,
    label: String,
    textMeasurer: TextSizeMeasurer
): Dp {
    return textMeasurer.calculateMaxWidth(texts = texts + label)
}

// Function to render the header row
@Composable
private fun _RenderHeaderRow(
    modifier: Modifier,
    labels: List<String>,
    columnWidths: List<Dp>,
    style: TextStyle,
    verticalGap: Dp
) {
    Row(
        modifier = modifier
    ) {
        labels.forEachIndexed { index, label ->
            Box(
                modifier = Modifier
                    .width(columnWidths[index])//extra for padding+rounded corner
            ) {
                Text(
                    text = label,
                    style = style.copy(fontWeight = FontWeight.Bold), // Bold or emphasize the header text
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onPrimary, // Text color for contrast
                    modifier = Modifier.align(Alignment.Center) // Center the text within the box
                )
            }
            if (index < labels.size - 1) {
                Spacer(Modifier.width(verticalGap))
            }
        }
    }
}

@Composable
private fun _RenderExamRow(
    modifier: Modifier,
    exam: ExamDetailsModel,
    columnWidths: List<Dp>,
    style: TextStyle,
    verticalGap: Dp
) {
    Row(
        modifier = modifier
    ) {
        _CenteredText(
            text = exam.date,
            width = columnWidths[0],
            style = style,
        )
        Spacer(Modifier.width(verticalGap))
        _CenteredText(
            text = exam.courseCode,
            width = columnWidths[1],
            style = style
        )
        Spacer(Modifier.width(verticalGap))
        _CenteredText(
            text = exam.courseTitle,
            width = columnWidths[2],
            style = style
        )
        Spacer(Modifier.width(verticalGap))
        _CenteredText(
            text = exam.time,
            width = columnWidths[3],
            style = style
        )
    }
}

@Composable
private fun _CenteredText(text: String, width: Dp, style: TextStyle) {
    Box(
        modifier = Modifier
            .width(width)
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.secondaryContainer),
        contentAlignment = Alignment.Center // Centers the content inside the Box
    ) {
        Text(
            text = text,
            style = style,
            textAlign = TextAlign.Center
        )
    }
}