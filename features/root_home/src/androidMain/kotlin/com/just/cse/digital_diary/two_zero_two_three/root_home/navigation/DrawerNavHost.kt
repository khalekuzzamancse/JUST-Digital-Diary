package com.just.cse.digital_diary.two_zero_two_three.root_home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.destination_provider.AdminOfficeSubOfficeDestinations
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.destination_provider.AuthDestinations
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.destination_provider.DepartmentInfoModuleDestinations
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.destination_provider.TopMostDestinations

@Composable
fun DrawerNavHost(
    modifier: Modifier = Modifier,
    appEvent: AppEvent,
    openDrawerRequest: () -> Unit,
    onNoteCreationRequest: () -> Unit,
    onDepartmentInfoRequest: (String) -> Unit,
    onAdminOfficeSubOfficeRequest: (subOfficeId: String) -> Unit,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = TopMostDestinations.HOME.route
    ) {

        composable(route = TopMostDestinations.HOME.route) {
            TopMostDestinations.Home(
                onCreateNoteRequest = onNoteCreationRequest,
                onOpenDrawerRequest = openDrawerRequest
            ) {
            }

        }

        composable(route = TopMostDestinations.FACULTY_LIST.route) {
            TopMostDestinations.FacultyList(
                onExitRequest = openDrawerRequest,
                onDepartmentInfoRequest = onDepartmentInfoRequest
            )
        }
        composable(route = TopMostDestinations.ADMIN_OFFICES.route) {
            TopMostDestinations.AdminOfficeList(
                onExitRequest = openDrawerRequest,
                onEmployeeListRequest = onAdminOfficeSubOfficeRequest
            )
        }
        composable(route = AdminOfficeSubOfficeDestinations.SUB_OFFICE_EMPLOYEES.route) {
            AdminOfficeSubOfficeDestinations.AdminOfficeSubOfficeEmployees(
                subOfficeId = "01",
                appEvent = appEvent,
                onExitRequest = openDrawerRequest
            )
        }
        composable(route = TopMostDestinations.SEARCH.route) {
            TopMostDestinations.Search(
                onExitRequest = openDrawerRequest,
                onCallRequest = appEvent.onCallRequest,
                onEmailRequest = appEvent.onEmailRequest,
                onMessageRequest = appEvent.onMessageRequest
            )
        }
        composable(route = TopMostDestinations.EVENT_GALLERY.route) {
            TopMostDestinations.EventGallery(
                onExitRequest = openDrawerRequest
            )


        }
        composable(route = TopMostDestinations.MESSAGE_FROM_VC.route) {
            TopMostDestinations.MessageFromVC(
                onExitRequest = openDrawerRequest
            )

        }

        composable(route = TopMostDestinations.NOTE_LIST.route) {
            TopMostDestinations.NoteList(
                onExitRequest = openDrawerRequest,
            )
        }
        composable(route = TopMostDestinations.ABOUT_US.route) {
            TopMostDestinations.AboutUs(
                onExitRequest = openDrawerRequest
            )
        }
        composable(route = TopMostDestinations.NOTE_CREATION.route) {
            TopMostDestinations.CreateNote(
                onExitRequest = openDrawerRequest
            )
        }

        composable(route = DepartmentInfoModuleDestinations.DEPARTMENT_INFO.route) {
            DepartmentInfoModuleDestinations.DepartmentInfo(
                departmentId = "01", appEvent = appEvent, onExitRequest = openDrawerRequest
            )

        }


    }

}
