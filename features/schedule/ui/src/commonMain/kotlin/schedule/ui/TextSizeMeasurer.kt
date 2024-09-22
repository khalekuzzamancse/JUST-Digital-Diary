package schedule.ui

import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import schedule.ui.model.ClassDetailModel

class TextSizeMeasurer(
    private val measurer: TextMeasurer,
    private val style: TextStyle,
    private val density: Density
) {

    private val safePaddingWidth = 20.dp
    private val safePaddingHeight = 20.dp
    private val dayLabelPadding = 10.dp

    fun calculateWidthPerHour(courses: List<ClassDetailModel>): Dp {
        val maxMeasuredTextWidth = courses.maxOfOrNull { it.routeItemWidth() }?:80
        val maxTextWidthInDp = with(density) { maxMeasuredTextWidth.toDp() }
        return maxTextWidthInDp + safePaddingWidth
    }

    fun calculateMaxWidth(texts: List<String>): Dp {
        val maxWidth = texts.maxOfOrNull { measureWidth(it) }?:80
        return with(density) { maxWidth.toDp() } + dayLabelPadding
    }

    fun calculateMaxHeight(courses: List<ClassDetailModel>): Dp {
        val maxMeasuredTextHeight = courses.maxOfOrNull { it.measureHeight() }?:80
        val maxTextHeightInDp = with(density) { maxMeasuredTextHeight.toDp() }
        return maxTextHeightInDp + safePaddingHeight
    }

    private fun ClassDetailModel.routeItemWidth(): Int {
        return listOf(
            measureWidth(this.time),
            measureWidth(this.courseCode),
            measureWidth(this.teacherShortName)
        ).max()
    }

    private fun ClassDetailModel.measureHeight(): Int {
        return measureHeight(this.time) +
                measureHeight(this.courseCode) +
                measureHeight(this.teacherShortName)
    }

    private fun measureWidth(text: String): Int {
        return measurer.measure(text = text, style = style).size.width
    }

    private fun measureHeight(text: String): Int {
        return measurer.measure(text = text, style = style).size.height
    }
}
