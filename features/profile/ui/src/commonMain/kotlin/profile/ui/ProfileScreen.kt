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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import profile.presentationlogic.ProfileEvent
import profile.presentationlogic.model.ProfileModel

@Composable
internal fun ProfileRoute(
    token: String?,
    onEvent: (ProfileEvent) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
            Dashboard(
                isAdmin = true,
                onCalendarUpdateClick = { onEvent(ProfileEvent.CalendarUpdate) },
                onExamRoutineUpdateClick = { onEvent(ProfileEvent.ExamRoutineUpdate) },
                onClassRoutineUpdateClick = { onEvent(ProfileEvent.ClassRoutineUpdate) },
                onFacultyInsertRequest = {
                    onEvent(ProfileEvent.InsertFacultyRequest)
                },
                onDeptInsertRequest = {
                    onEvent(ProfileEvent.InsertDepartmentRequest)
                },
                onTeacherInsertRequest = {
                    onEvent(ProfileEvent.InsertTeacherRequest)
                },

                )
        }

    }


//    if (token == null) {
//        EmptyContentScreen(
//            message = "Something is wrong,Please Log in again"
//        )
//    } else {
//        val viewModel = viewModel { ProfileViewModel(UiFactory.createProfileController(token)) }
//        val isNotFetching = !(viewModel.controller.isFetching.collectAsState().value)
//        SnackNProgressBarDecorator(
//            isLoading = viewModel.isLoading.collectAsState().value,
//            snackBarMessage = viewModel.screenMessage.collectAsState().value
//        ) {
//            val model = viewModel.controller.profile.collectAsState().value
//            if (model.isEmpty() && isNotFetching) {
//                EmptyContentScreen()
//            } else {
//                if (!model.isEmpty()) {
//                    Column(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        ProfileCard(profile = model)
//                        Spacer(Modifier.height(8.dp))
//                        Dashboard(
//                            isAdmin = true,
//                            onCalendarUpdateClick = { onEvent(ProfileEvent.NavigateToCalendarUpdate) },
//                            onExamRoutineUpdateClick = { onEvent(ProfileEvent.NavigateToExamRoutineUpdate) },
//                            onClassRoutineUpdateClick = { onEvent(ProfileEvent.NavigateToClassRoutineUpdate) },
//                            onTeacherInfoUpdateClick = { onEvent(ProfileEvent.NavigateToTeacherInfoUpdate) },
//                        )
//                    }
//                }
//
//
//            }
//        }
//    }


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
@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun Dashboard(
    isAdmin: Boolean = false,
    onCalendarUpdateClick: () -> Unit = {},
    onExamRoutineUpdateClick: () -> Unit = {},
    onClassRoutineUpdateClick: () -> Unit = {},
    onTeacherInsertRequest: () -> Unit,
    onDeptInsertRequest: () -> Unit,
    onFacultyInsertRequest: () -> Unit,
) {
    val dashboardItems = mutableListOf<DashboardItemData>().apply {
        if (isAdmin) {
            add(
                DashboardItemData(
                    text = "Insert Faculty",
                    icon = Icons.Outlined.School,
                    onClick = onFacultyInsertRequest
                )
            )
            add(
                DashboardItemData(
                    text = "Insert Department",
                    icon = Icons.Outlined.School,
                    onClick = onDeptInsertRequest
                )
            )
            add(
                DashboardItemData(
                    text = "Insert Teacher",
                    icon = Icons.Outlined.Person,
                    onClick = onTeacherInsertRequest
                )
            )
            add(
                DashboardItemData(
                    text = "Calendar Update",
                    icon = Icons.Outlined.CalendarToday,
                    onClick = onCalendarUpdateClick
                )
            )
//            add(
//                DashboardItemData(
//                    text = "Exam Routine Update",
//                    icon = Icons.Outlined.Schedule,
//                    onClick = onExamRoutineUpdateClick
//                )
//            )
            add(
                DashboardItemData(
                    text = "Class Routine Update",
                    icon = Icons.Outlined.Timer,
                    onClick = onClassRoutineUpdateClick
                )
            )

        }
    }
    val windows= calculateWindowSizeClass()
    val itemsPerRow = when ( windows.widthSizeClass ){
        WindowWidthSizeClass.Expanded-> 3 // Expanded screen: show at most 3 items per row
        else -> 2 // Compact screen: show at most 2 items per row
    }
    var maxWidth by remember { mutableStateOf(0) } // Store the largest width
    val density = LocalDensity.current // For converting pixels to dp

    FlowRow(
        modifier = Modifier
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        dashboardItems.chunked(itemsPerRow).forEach { rowItems ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
            ) {
                rowItems.forEach { item ->
                    _DashboardItem(
                        modifier = Modifier
                            .onGloballyPositioned { layoutCoordinates ->
                                // Measure the width of each item and update maxWidth if necessary
                                val width = layoutCoordinates.size.width
                                if (width > maxWidth) {
                                    maxWidth = width
                                }
                            }
                            .width(
                                if (maxWidth > 0) {
                                    with(density) { maxWidth.toDp() } // Apply maxWidth once it's available
                                } else {
                                    Dp.Unspecified // Initially don't specify a width
                                }
                            )
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
        contentAlignment = Alignment.Center
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

