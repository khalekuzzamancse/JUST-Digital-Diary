package com.just.cse.digital_diary.features.departments.navgraph

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.just.cse.digital_diary.features.departments.navgraph.child_destinations.DepartmentModule
import com.just.cse.digital_diary.features.departments.navgraph.local_destinations.search.SearchScreen


internal class NavigatorManager(
    private val navigator: Navigator?
) {
    companion object {
        private const val TAG = "DepartmentListModule_NavigatorManager ::"
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
    fun navigateDepartmentInfoModule(departmentId: String) {
        push(DepartmentModule(
            departmentId=departmentId,
            onExitRequest = ::pop
        ))
    }
    fun navigateSearchDestination() {
        push(
            SearchScreen(
                onExitRequest =::pop
            )
        )
    }

}
