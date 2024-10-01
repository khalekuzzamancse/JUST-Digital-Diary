
package miscellaneous.ui

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import common.ui.TypeWriter
import miscellaneous.presentationlogic.model.AboutUsModel

@Composable
fun AboutUsRoute() {
    val state: AboutUsModel = remember {
        AboutUsModel(
            appName = "JUST DIGITAL DIARY",
            developedDepartmentName = "Computer Science and Engineering",
            universityName = "Jashore University of Sciences and Technology(JUST)",
            otherInfo = ""
        )

    }
    _AboutUsState(state = state)


}

@Composable
private fun _AboutUsState(
    state: AboutUsModel,
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()

            .verticalScroll(rememberScrollState())
    ) {
        AppName(state.appName)
        Spacer(modifier = Modifier.height(8.dp))
        DeptAndUniversityName(
            deptName = state.developedDepartmentName,
            universityName = state.universityName
        )
    }


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
        append(" ")
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
        text = text,
        delay = 10
    ) {
        Text(
            text = it,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}

