package com.just.cse.digital_diary.two_zero_two_three.auth.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.just.cse.digital_diary.two_zero_two_three.auth.data.repository.FacultyRepository
import com.just.cse.digital_diary.two_zero_two_three.auth.data.repository.SectionRepository


@Composable
fun NavHost() {
    Navigator(
        screen = HomeNavHost()
    )

}

class HomeNavHost : Screen {
    private var navigator: Navigator? = null

    @Composable
    override fun Content() {//This is nav host
        Box(
            modifier = Modifier.size(500.dp)
        ) {
            navigator = LocalNavigator.current
            navigateToHome()
        }

    }
    private fun onDepartmentInfoAsk(id: String){
        val department=FacultyRepository.getDepartmentById(id)
        println("Department List:$department")
    }

    private fun navigateToDepartmentList(facultyId: String) {
        val departmentList = FacultyRepository.getDepartments(facultyId)
        val facultyInfo=FacultyRepository.getFacultyInfoList().find { it.id ==facultyId }
        navigator?.push(
            DepartmentList(
                departments = departmentList,
                navigateToDepartment = ::onDepartmentInfoAsk,
                destinationName = "$facultyInfo"

            )
        )
    }

    private fun navigateToSectionChild(sectionId: String) {
        val subSections = SectionRepository.getSectionChild(sectionId)
        val destinationInfo=SectionRepository.getSection(sectionId)

        navigator?.push(
            SectionChildScreen(
                subSections = subSections,
                onSubSectionSelected = ::navigateToDepartmentList,
                destinationName = "$destinationInfo"
            )
        )
    }

    private fun navigateToHome() {
        navigator?.push(
            Home(
                onFacultySelected = ::navigateToSectionChild
            )
        )
    }


}

/*
  val navigateToDepartmentList: (String) -> Unit = {
        val departments = FacultyRepository.getDepartments(it)
        println(departments)
        navigator?.push(DepartmentList(departments))
    }
    HomeDestination(
        onFacultySelected = navigateToDepartmentList
    )
 */

