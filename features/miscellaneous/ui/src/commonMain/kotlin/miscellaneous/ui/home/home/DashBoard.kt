import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.SupervisorAccount
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class DashboardItemData(
    val text: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

// Root Dashboard Function with default lambdas for each event
@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Composable
fun Dashboard(
    isAdmin: Boolean = false,
    onFacultyListClick: () -> Unit = {},
    onAdminOfficerListClick: () -> Unit = {},
    onCalendarUpdateClick: () -> Unit = {},
    onExamRoutineUpdateClick: () -> Unit = {},
    onClassRoutineUpdateClick: () -> Unit = {},
    onTeacherInfoUpdateClick: () -> Unit = {}
) {
    val dashboardItems = mutableListOf<DashboardItemData>().apply {
        add(DashboardItemData("Faculty List", Icons.Default.People, onFacultyListClick))
        add(
            DashboardItemData(
                "Admin Officer List",
                Icons.Default.SupervisorAccount,
                onAdminOfficerListClick
            )
        )
        if (isAdmin) {
            add(
                DashboardItemData(
                    "Calendar Update",
                    Icons.Default.CalendarToday,
                    onCalendarUpdateClick
                )
            )
            add(
                DashboardItemData(
                    "Exam Routine Update",
                    Icons.Default.Schedule,
                    onExamRoutineUpdateClick
                )
            )
            add(
                DashboardItemData(
                    "Class Routine Update",
                    Icons.Default.Timer,
                    onClassRoutineUpdateClick
                )
            )
            add(
                DashboardItemData(
                    "Teacher Info Update",
                    Icons.Default.Person,
                    onTeacherInfoUpdateClick
                )
            )
        }
    }
    FlowRow(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        dashboardItems.forEachIndexed { index, item ->
            DashboardItem(
                modifier = Modifier
                    .weight(1f)
                    .clickable { item.onClick() }
                    .shadow(8.dp, RoundedCornerShape(12.dp))
                    .background(
                        Color.White,
                        RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp),
                text = item.text,
                icon = item.icon,
            )


        }

    }
}

@Composable
private fun DashboardItem(
    modifier: Modifier = Modifier,
    text: String, icon: ImageVector,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        _DashboardItem(
            text, icon
        )
    }
}

// Reusable composable for displaying dashboard items
@Composable
private fun _DashboardItem(
    text: String, icon: ImageVector,
) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, style = MaterialTheme.typography.labelSmall)
    }
}


