package com.just.cse.digital_diary.two_zero_two_three.auth.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.just.cse.digital_diary.two_zero_two_three.auth.data.repository.Department
import com.just.cse.digital_diary.two_zero_two_three.auth.data.repository.FacultyInfo
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destination.department_destionation.DepartmentListDestination
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destination.home_screen.HomeScreen
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destination.sub_section_destionation.SectionChild

class DepartmentList(
    private val destinationName: String="",
    private val departments: List<Department>,
    private val navigateToDepartment:(String)->Unit,

) : Screen {
    @Composable
    override fun Content() {
        DepartmentListDestination(
            departments = departments,
            navigateToDepartment = navigateToDepartment,//index
            content = {
                Text("Welcome to :$destinationName")
            },
        )
    }
}

class SectionChildScreen(
    private val destinationName: String="",
    private val subSections: List<FacultyInfo>,
    private val onSubSectionSelected: (String) -> Unit,
) : Screen {
    @Composable
    override fun Content() {
        SectionChild(
            subSections,
            navigateToSectionChild = onSubSectionSelected,
            content = {
                Text("Welcome to :$destinationName")
            }
        )
    }

}

class Home(
    private val onFacultySelected: (String) -> Unit,
) : Screen {

    @Composable
    override fun Content() {
        HomeScreen(
            navigateToSection = {
                onFacultySelected(it)
            },
        )

    }
}