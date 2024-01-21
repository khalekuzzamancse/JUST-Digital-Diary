package com.just.cse.digital_diary.two_zero_two_three.root_home.local_destionations.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar
import com.just.cse.digital_diary.two_zero_two_three.employee_list.searchable_employee_list.SearchableEmployeeList
import com.just.cse.digital_diary.two_zero_two_three.root_home.local_destionations.about_us.AppDescription
import com.just.cse.digital_diary.two_zero_two_three.root_home.local_destionations.about_us.AppName
import com.just.cse.digital_diary.two_zero_two_three.root_home.local_destionations.about_us.ContactSection
import com.just.cse.digital_diary.two_zero_two_three.root_home.local_destionations.about_us.FeaturesSection
import com.just.cse.digitaldiary.twozerotwothree.data.repository.department_info.DepartmentInfoRepository

@Composable
fun SearchScreen(
    onExitRequest: () -> Unit,
) {
    SearchableEmployeeList(
        onSearchExitRequest = onExitRequest,
        employeeList = DepartmentInfoRepository.getTeacherList("01"),
        onEmailRequest = {},
        onMessageRequest = {},
        onCallRequest = {}
    )


}