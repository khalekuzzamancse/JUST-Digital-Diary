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
import androidx.compose.material.icons.outlined.AdminPanelSettings
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.School
import navigation.Destination
import navigation.NavGroup
import navigation.NavigationItem

//TODO



sealed interface NavDestination : Destination {
    data object Home : NavDestination {
        override val route = "Home"
    }
    data object Profile : NavDestination {
        override val route = "Profile"
    }
    data object ProfileNavGraph : NavDestination {
        override val route = "ProfileNavGraph"
    }
    data object FacultyList : NavDestination {
        override val route = "FacultyList"
    }
    data object AdminOffice : NavDestination {
        override val route = "AdminOffice"
    }
    data object Search : NavDestination {
        override val route = "Search"
    }
    data object EventGallery : NavDestination {
        override val route = "EventGallery"
    }
    data object ExploreJust : NavDestination {
        override val route = "ExploreJust"
    }
    data object NoteBook : NavDestination {
        override val route = "NoteBook"
    }
    data object MessageFromVC : NavDestination {
        override val route = "MessageFromVC"
    }
    data object AboutUs : NavDestination {
        override val route = "AboutUs"
    }
    data object ExamSchedule : NavDestination {
        override val route = "ExamSchedule"
    }
    data object ClassSchedule : NavDestination {
        override val route = "ClassSchedule"
    }
    //Defining admin destination
    data object InsertFaculty : NavDestination {
        override val route = "InsertFaculty"
    }
    data object UpdateFaculty : NavDestination {
        override val route = "UpdateFaculty"
    }
    data object InsertDept : NavDestination {
        override val route = "InsertDept"
    }
    data object UpdateDept : NavDestination {
        override val route = "UpdateDept"
    }
    data object InsertTeacher : NavDestination {
        override val route = "InsertTeacher"
    }
    data object UpdateTeacher : NavDestination {
        override val route = "UpdateTeacher"
    }
    data object UpdateExamSchedule : NavDestination {
        override val route = "UpdateExamSchedule"
    }
    data object UpdateClassSchedule : NavDestination {
        override val route = "UpdateClassSchedule"
    }
    data object UpdateAcademicCalender : NavDestination {
        override val route = "UpdateAcademicCalender"
    }

}


object NavDestinationBuilder {

    val navGroups = listOf(group1(), group2(), group3(), group4(), group5())

    val allDestinations: List<Destination> = navGroups.flatMap { group ->
        group.items.map { it.destination }
    }


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

