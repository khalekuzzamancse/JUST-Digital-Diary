package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.destination.about_us

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.animation.TypeWriter

@Composable
internal fun ColumnScope.AppName(
    name: String
) {
    Text(
        text = name,
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.align(Alignment.CenterHorizontally)
    )
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
internal fun DeptAndUniversityName(
    deptName: String,
    universityName: String,
) {

    val text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,

            )
        ) {
            append("Developed by\n")
        }
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                color = Color.Green
            )
        ) {
            append(deptName)
        }
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        ) {
            append(universityName)
        }

    }

    TypeWriter(
        text=text,
        delay = 10){
        Text(
            text = it,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}

