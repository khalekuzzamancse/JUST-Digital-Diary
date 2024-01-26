package com.just.cse.digital_diary.two_zero_two_three.root_home

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.destination.about_us.AboutUsDestination
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.destination.message_from_vc.MessageFromVCDestination
import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.repoisitory.OtherInfoRepositoryImpl
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
        AboutUsDestination(
            repository=OtherInfoRepositoryImpl()
        )
//        MessageFromVCDestination(
//            repository = OtherInfoRepositoryImpl(),
//            onExitRequest = {}
//        )
  }


}



