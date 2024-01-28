package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.component.department_list.state

data class DepartmentListState(
    val title: String? = null,
    val enableBackNavigation: Boolean = true,
    val departments: List<Department> = emptyList(),
    val selected: Int = -1
)