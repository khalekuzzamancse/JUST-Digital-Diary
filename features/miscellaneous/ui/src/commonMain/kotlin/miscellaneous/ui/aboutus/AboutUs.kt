package miscellaneous.ui.aboutus

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import common.ui.WindowSizeDecorator
import common.ui.animation.TypeWriter


@Composable
internal fun _AboutUsState(
    state: AboutUsState,
) {
    WindowSizeDecorator(
        onCompact = {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()

                    .verticalScroll(rememberScrollState())
            ) {
                AppName(state.appName)
                DeptAndUniversityName(
                    deptName = state.developedDepartmentName,
                    universityName = state.universityName
                )
            }

        },
        onNonCompact = {
            Column(
                modifier = Modifier
                    .padding(48.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                AppName(state.appName)
                DeptAndUniversityName(
                    deptName = state.developedDepartmentName,
                    universityName = state.universityName
                )
            }

        },
    )


}
@Composable
private fun ColumnScope.AppName(
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

