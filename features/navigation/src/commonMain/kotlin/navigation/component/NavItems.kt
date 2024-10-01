package navigation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AdminPanelSettings
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.Search
import common.ui.Destination
import common.ui.NavGroup
import common.ui.NavigationItem


sealed interface NavDestination : Destination {
    data object Home : NavDestination
    data object FacultyList : NavDestination
    data object AdminOffice : NavDestination
    data object Search : NavDestination
    data object EventGallery : NavDestination
    data object ExploreJust : NavDestination
    data object NoteBook : NavDestination
    data object MessageFromVC : NavDestination
    data object AboutUs : NavDestination
    data object ExamSchedule : NavDestination
    data object ClassSchedule:NavDestination
}

object NavDestinationBuilder {

    val navGroups = listOf(group1(), group2(), group3(), group4(), group5())

    private fun group1() = NavGroup(
        items = listOf(
            NavigationItem(
                label = "Home",
                unFocusedIcon = Icons.Outlined.Home,
                focusedIcon = Icons.Filled.Home,
                destination = NavDestination.Home
            ),
        )
    )

    private fun group2() = NavGroup(
        items = listOf(
            NavigationItem(
                label = "Faculty List",
                unFocusedIcon = Icons.Outlined.School,
                focusedIcon = Icons.Filled.School,
                destination = NavDestination.FacultyList
            ),
            NavigationItem(
                label = "Admin Offices",
                unFocusedIcon = Icons.Outlined.AdminPanelSettings,
                focusedIcon = Icons.Filled.AdminPanelSettings,
                destination = NavDestination.AdminOffice
            ),
        )
    )



    private fun group3() = NavGroup(
        items = listOf(
//            NavigationItem(
//                label = "NoteBook",
//                unFocusedIcon = Icons.AutoMirrored.Outlined.Notes,
//                focusedIcon = Icons.AutoMirrored.Filled.Notes,
//                destination = NavDestination.NoteBook
//            ),
            NavigationItem(
                label = "Class Schedule",
                unFocusedIcon =   Icons.Outlined.CalendarMonth,
                focusedIcon =  Icons.Filled.CalendarMonth,
                destination = NavDestination.ClassSchedule
            ),
            NavigationItem(
                label = "Exam Schedule",
                unFocusedIcon = Icons.Outlined.CalendarToday,
                focusedIcon = Icons.Filled.CalendarToday,
                destination = NavDestination.ExamSchedule
            ),
        )
    )
    private fun group4() = NavGroup(
        items = listOf(
//            NavigationItem(
//                label = "Search",
//                unFocusedIcon = Icons.Outlined.Search,
//                focusedIcon = Icons.Filled.Search,
//                destination = NavDestination.Search
//            ),
            NavigationItem(
                label = "Event Gallery",
                unFocusedIcon = Icons.Outlined.Image,
                focusedIcon = Icons.Filled.Image,
                destination = NavDestination.EventGallery
            ),
            NavigationItem(
                label = "Explore JUST",
                unFocusedIcon = Icons.Outlined.Explore,
                focusedIcon = Icons.Filled.Explore,
                destination = NavDestination.ExploreJust
            ),
        )
    )
    private fun group5() = NavGroup(
        items = listOf(
            NavigationItem(
                label = "Message From VC",
                unFocusedIcon = Icons.AutoMirrored.Outlined.Message,
                focusedIcon = Icons.AutoMirrored.Filled.Message,
                destination = NavDestination.MessageFromVC
            ),
            NavigationItem(
                label = "About Us",
                unFocusedIcon = Icons.Outlined.Info,
                focusedIcon = Icons.Filled.Info,
                destination = NavDestination.AboutUs
            ),
        )
    )
}

