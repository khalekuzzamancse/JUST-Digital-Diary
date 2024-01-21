package com.just.cse.digital_diary.features.faculty.faculty.navigation

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.just.cse.digital_diary.features.faculty.faculty.navigation.child_destinations.DepartmentModule
import com.just.cse.digital_diary.features.faculty.faculty.navigation.local_destinations.search.SearchScreen
import com.just.cse.digital_diary.two_zero_two_three.department.DepartmentModuleEvent


internal class NavigatorManager(
    private val navigator: Navigator?
) {
    companion object {
        private const val TAG = "FacultyModule_NavigatorManager ::"
        private fun log(message: String) {
            println("$TAG$message")
        }
    }

    private fun pop() {
        if (navigator == null)
            log("Can not Pop()")
        else
            navigator.pop()

    }

    private fun push(des: Screen) {
        if (navigator == null)
            log("push()->can not push")
        else
            navigator.push(des)

    }
//    fun navigateDepartmentsModule(facultyId: String) {
//        push(
//            DepartmentListModuleEntryPoint(
//                facultyId = facultyId,
//                onExitRequest =::pop
//            )
//        )
//    }
    fun navigateSearchDestination() {
        push(
            SearchScreen(
                onExitRequest =::pop
            )
        )
    }
    fun navigateDepartmentInfoModule(departmentId: String,event: DepartmentModuleEvent) {
        push(
            DepartmentModule(
            departmentId=departmentId,
            onExitRequest = ::pop,
                event = event
        )
        )
    }

}
/*
Maintain separate navigation and backstack so that for the faculty module
so that when use click on the next back button he did not go exit from the app.
in future requirements may added...
 */

