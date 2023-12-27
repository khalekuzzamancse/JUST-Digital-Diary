//package com.just.cse.digital_diary.two_zero_two_three.auth.ui.navigation
//
//import androidx.compose.runtime.Composable
//import cafe.adriel.voyager.core.screen.Screen
//import com.just.cse.digital_diary.two_zero_two_three.auth.data.repository.Department
//import com.just.cse.digital_diary.two_zero_two_three.auth.data.repository.FacultyInfo
//import com.just.cse.digital_diary.two_zero_two_three.auth.data.repository.generateDummyEmployeeList
//import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destination.department_destionation.DepartmentListDestination
//import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destination.employee_list.EmployeeList
//import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destination.top_most_home_destination.HomeScreen
//import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destination.sub_section_destionation.SectionChild
//
//class DepartmentList(
//    private val departments: List<Department>,
//    private val navigateToDepartment:(String)->Unit,
//
//) : Screen {
//    @Composable
//    override fun Content() {
//        DepartmentListDestination(
//            departments = departments,
//            navigateToDepartment = navigateToDepartment,//index
//            content = {
//                EmployeeList(
//                    employees = generateDummyEmployeeList(10)
//                )
//            },
//        )
//    }
//}
//
//class SectionChildScreen(
//    private val destinationName: String="",
//    private val subSections: List<FacultyInfo>,
//    private val onSubSectionSelected: (String) -> Unit,
//) : Screen {
//    @Composable
//    override fun Content() {
//        SectionChild(
//            subSections,
//            navigateToSectionChild = onSubSectionSelected,
//            content = {
//                EmployeeList(
//                    employees = generateDummyEmployeeList(10)
//                )
//            }
//        )
//    }
//
//}
//
//class Home(
//    private val onFacultySelected: (String) -> Unit,
//) : Screen {
//
//    @Composable
//    override fun Content() {
//        HomeScreen(
//            navigateToSection = {
//                onFacultySelected(it)
//            },
//        )
//
//    }
//}