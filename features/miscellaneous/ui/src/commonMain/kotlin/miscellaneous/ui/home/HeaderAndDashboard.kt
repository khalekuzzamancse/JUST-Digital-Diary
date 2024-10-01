package miscellaneous.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.SupervisorAccount
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import justdigitaldiary.features.miscellaneous.ui.generated.resources.Res
import justdigitaldiary.features.miscellaneous.ui.generated.resources.just_logo_trans
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

/**
 * * Needed refactor
 * * Since right now unable to read image as shared source,that is why delegating
 * the platform to load the image from their own resource and the logo composable.
 * fix it later using "Res" class  of compose multiplatform resource
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    onEvent: (HomeRouteEvent) -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val res: DrawableResource = Res.drawable.just_logo_trans
        //after that compile it again to generate Res class, use : .\gradlew generateComposeResClass
        Image(
            modifier = Modifier.size(50.dp)
                .background(Color.White),//will work for both dark and light
            painter = painterResource(res),//org.jetbrains.compose.resources.
            contentDescription = "JUST",
        )
        AppNameLogoSection(modifier = Modifier)
        Spacer(Modifier.height(8.dp))
        Dashboard(
            isAdmin = true,
            onFacultyListClick = { onEvent(HomeRouteEvent.NavigateToFacultyList) },
            onAdminOfficerListClick = { onEvent(HomeRouteEvent.NavigateTAdminOfficeList) },
            onCalendarUpdateClick = { onEvent(HomeRouteEvent.NavigateToCalendarUpdate) },
            onExamRoutineUpdateClick = { onEvent(HomeRouteEvent.NavigateToExamRoutineUpdate) },
            onClassRoutineUpdateClick = { onEvent(HomeRouteEvent.NavigateToClassRoutineUpdate) },
            onTeacherInfoUpdateClick = { onEvent(HomeRouteEvent.NavigateToTeacherInfoUpdate) },
        )


    }


}

@Composable
private fun _FacultyListButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Default.School,
            contentDescription = "faculty list",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun _AdminOfficeListButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Default.AdminPanelSettings,
            contentDescription = "faculty list",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}


@Composable
private fun AppNameLogoSection(
    modifier: Modifier = Modifier
) {

    val text = buildAnnotatedString {
        append(
            AnnotatedString(
                text = "J",
                spanStyle = SpanStyle(MaterialTheme.colorScheme.primary)
            )
        )
        append(
            AnnotatedString(
                text = "U",
                spanStyle = SpanStyle(MaterialTheme.colorScheme.secondary)
            )
        )
        append(
            AnnotatedString(
                text = "S",
                spanStyle = SpanStyle(MaterialTheme.colorScheme.primary)
            )
        )
        append(
            AnnotatedString(
                text = "T",
                spanStyle = SpanStyle(MaterialTheme.colorScheme.secondary)
            )
        )
        append(
            AnnotatedString(
                text = " Digital",
                spanStyle = SpanStyle(MaterialTheme.colorScheme.secondary)
            )
        )
        append(
            AnnotatedString(
                text = " Diary",
                spanStyle = SpanStyle(MaterialTheme.colorScheme.primary)
            )
        )
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp,
                fontFamily = FontFamily.Cursive
            )
        )

    }

}

//TODO:Dashboard section

private data class DashboardItemData(
    val text: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

// Root Dashboard Function with default lambdas for each event
@OptIn(ExperimentalLayoutApi::class)
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
        add(DashboardItemData(
            text = "Faculty List",
            icon = Icons.Outlined.People,
            onClick = onFacultyListClick
        ))
        add(
            DashboardItemData(
                text = "Admin Officer List",
                icon = Icons.Outlined.SupervisorAccount,
                onClick = onAdminOfficerListClick
            )
        )
        if (isAdmin) {
            add(
                DashboardItemData(
                    text = "Calendar Update",
                    icon = Icons.Outlined.CalendarToday,
                    onClick = onCalendarUpdateClick
                )
            )
            add(
                DashboardItemData(
                    text = "Exam Routine Update",
                    icon = Icons.Outlined.Schedule,
                    onClick = onExamRoutineUpdateClick
                )
            )
            add(
                DashboardItemData(
                    text = "Class Routine Update",
                    icon = Icons.Outlined.Timer,
                    onClick = onClassRoutineUpdateClick
                )
            )
            add(
                DashboardItemData(
                    text = "Teacher Info Update",
                    icon = Icons.Outlined.Person,
                    onClick = onTeacherInfoUpdateClick
                )
            )
        }
    }
    FlowRow(
        modifier = Modifier
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        dashboardItems.forEachIndexed { _, item ->
            _DashboardItem(
                modifier = Modifier
                    .weight(1f)
                    .clickable { item.onClick() }
                    .shadow(8.dp, RoundedCornerShape(12.dp))
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp),
                text = item.text,
                icon = item.icon,
            )


        }

    }
}

@Composable
private fun _DashboardItem(
    modifier: Modifier = Modifier,
    text: String, icon: ImageVector,
) {
    val color=MaterialTheme.colorScheme.secondary //clickable so importance is high
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "dashboard item",
                tint =color
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall,
                color = color
            )
        }
    }
}



