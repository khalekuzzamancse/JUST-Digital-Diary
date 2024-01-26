package com.just.cse.digital_diary.two_zero_two_three.root_home

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.destination.destination.AdminOfficesDestination
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.destination.viewmodel.AdminOfficesDestinationViewModel
import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.repoisitory.AdminOfficerListRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_offices.repoisitory.AdminOfficeListRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.root_home.ui.themes.AppTheme


@Composable
fun RootModule(appEvent: AppEvent) {
    AppTheme {
       // AuthScreen()
//        FacultiesScreen(onDepartmentSelected = {
//            println("Hoome:SelectedDepartment$it")
//        })
        //TeacherList()
//        DepartmentModuleEntryPoint(
//            departmentId = "01",
//        )
//        AboutUsDestination(
//            repository=OtherInfoRepositoryImpl()
//        )
//        EventGalleryDestination(
//            repository=OtherInfoRepositoryImpl()
//        )
//
        AdminOfficesDestination(
            viewModel = AdminOfficesDestinationViewModel(
                repository = AdminOfficeListRepositoryImpl()
            )
        )
//        MessageFromVCDestination(
//            repository = OtherInfoRepositoryImpl(),
//            onExitRequest = {}
//        )
  }


}



