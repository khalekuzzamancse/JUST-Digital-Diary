package com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.form.FormTextFieldProperties
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.form.FormTextFieldState
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.form.FormTextInput
import kotlin.math.max


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AuthTextFieldPreview() {
    val properties = remember {
        FormTextFieldProperties(
            label = "User Name",
        )
    }

    var state by remember {
        mutableStateOf(
            FormTextFieldState()
        )
    }
    val onValueChanged: (String) -> Unit = {
        state = state.copy(
            value = it,
        )
    }

    AuthTextField(
        properties = properties,
        state = state,
        onValueChanged = onValueChanged,
        isHorizontalOrientation = calculateWindowSizeClass()
            .widthSizeClass!=WindowWidthSizeClass.Compact,
        labelMinWidth = 100.dp
        )
}

@Composable
fun AuthTextField(
    properties: FormTextFieldProperties,
    state: FormTextFieldState,
    isHorizontalOrientation: Boolean,
    labelMinWidth:Dp,
    onValueChanged: (String) -> Unit,
    onTrailingIconClick:()->Unit={},
) {

    FormTextInput(
        properties = properties.copy(
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Unspecified,
                unfocusedIndicatorColor = Color.Unspecified
            ),
        ),
        value = state.value,
        errorMessage = state.errorMessage,
        onValueChanged = onValueChanged,
        onTrailingIconClick =onTrailingIconClick ,
        labelFieldLayout = { label, field ->
            if (isHorizontalOrientation) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    label(Modifier.defaultMinSize(minWidth = labelMinWidth))
                    Spacer(Modifier.width(4.dp))
                    field(Modifier)
                }
            } else {
                Column {
                    label(Modifier)
                    Spacer(Modifier.height(2.dp))
                    field(Modifier)
                }
            }

        }
    )
}

@Composable
private fun FormLayout(
    eachRow1stChildMaxWidth: Dp,
    verticalGap: Dp = 5.dp,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier, content = content
    ) { measurables, constraints ->
        val firstColumnChildMeasureAbles = measurables.filterIndexed { i, measurable -> i % 2 == 0 }
        val secondColumnChildMeasureAbles =
            measurables.filterIndexed { i, measurable -> i % 2 == 1 }
        val eachRow1stChildConstraint = Constraints(
            minWidth = 0,
            minHeight = 0,
            maxWidth = eachRow1stChildMaxWidth.toPx().toInt(),
            maxHeight = Constraints.Infinity
        )
        val firstColumnChildPlaceAbles = firstColumnChildMeasureAbles.map { measurable ->
            measurable.measure(eachRow1stChildConstraint)
        }
        //let gap between two column=5dp

        val firstColumnWidth =
            firstColumnChildPlaceAbles.maxOf { it.width + verticalGap.toPx().toInt() }
        val secondColumnWidth = constraints.maxWidth - firstColumnWidth
        val eachRowSecondChildConstraint = Constraints(
            minWidth = secondColumnWidth,
            minHeight = 0,
            maxWidth = secondColumnWidth,
            maxHeight = Constraints.Infinity
        )
        val secondColumnChildPlaceAbles = secondColumnChildMeasureAbles.map { measurable ->
            measurable.measure(eachRowSecondChildConstraint)
        }
        val layoutHeight = max(
            firstColumnChildPlaceAbles.sumOf { it.height },
            secondColumnChildPlaceAbles.sumOf { it.height }
        )
        layout(constraints.maxWidth, layoutHeight) {
            var y = 0;
            firstColumnChildPlaceAbles.forEachIndexed { i, label ->
                val textField = secondColumnChildPlaceAbles[i]
                val rowHeight = max(label.height, textField.height)
                val eachRow1stChildMoveDown = (rowHeight - label.height) / 2
                val eachRow2ndChildMoveDown = (rowHeight - textField.height) / 2
                label.placeRelative(0, y + eachRow1stChildMoveDown)
                textField.placeRelative(firstColumnWidth, y + eachRow2ndChildMoveDown)
                y += rowHeight
            }

        }
    }
}
