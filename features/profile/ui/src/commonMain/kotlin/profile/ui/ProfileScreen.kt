package profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import profile.presentationlogic.ProfileEvent
import profile.presentationlogic.factory.UiFactory
import profile.presentationlogic.model.ProfileModel
import profile.ui.common.SnackNProgressBarDecorator

@Composable
internal fun ProfileRoute(
    token: String?,
    onEvent:( ProfileEvent)->Unit
) {

    if (token == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Text(text = "Something is wrong,Please Log in again")
        }

    } else {
        val viewModel = viewModel { ProfileViewModel(UiFactory.createProfileController(token)) }

        SnackNProgressBarDecorator(
            isLoading = viewModel.isLoading.collectAsState().value,
            snackBarMessage = viewModel.screenMessage.collectAsState().value
        ) {
            val model = viewModel.controller.profile.collectAsState().value
            if (!model.isEmpty()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProfileCard(profile = model)
                    Spacer(Modifier.height(8.dp))
                    Dashboard(
                        isAdmin = true,
                        onCalendarUpdateClick = { onEvent(ProfileEvent.NavigateToCalendarUpdate) },
                        onExamRoutineUpdateClick = { onEvent(ProfileEvent.NavigateToExamRoutineUpdate) },
                        onClassRoutineUpdateClick = { onEvent(ProfileEvent.NavigateToClassRoutineUpdate) },
                        onTeacherInfoUpdateClick = { onEvent(ProfileEvent.NavigateToTeacherInfoUpdate) },
                    )
                }


            }

        }
    }

}

@Composable
private fun ProfileCard(profile: ProfileModel) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .wrapContentWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = profile.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "@${profile.username}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = profile.email,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
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
    onCalendarUpdateClick: () -> Unit = {},
    onExamRoutineUpdateClick: () -> Unit = {},
    onClassRoutineUpdateClick: () -> Unit = {},
    onTeacherInfoUpdateClick: () -> Unit = {}
) {
    val dashboardItems = mutableListOf<DashboardItemData>().apply {
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
    val color = MaterialTheme.colorScheme.secondary //clickable so importance is high
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
                tint = color
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

