package schedule.ui.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun DepartmentSessionHeader(
    modifier: Modifier,
    departmentName: String, session: String,
    year: String, semester: String
) {
    Column(
        modifier = modifier
    ) {
        if (departmentName.isNotBlank()) {
            Text(
                text = "Department of $departmentName",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )
        }

        if (session.isNotBlank() && year.isNotBlank() && semester.isNotBlank()) {
            Text(
                text = "Session $session ( $year th year - $semester semester )",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                textAlign = TextAlign.Center, // Center text in the column
                modifier = Modifier
            )
        }


    }
}
