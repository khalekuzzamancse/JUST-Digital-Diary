package faculty.route

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common.ui.TopBarDecoratorCommon

import faculty.di.FacultyComponentProvider
import faculty.ui.teachers.teacherlist.component.TeacherListEvent
import faculty.ui.teachers.teacherlist.route.TeacherList


@Composable
internal fun TeachersScreen(
    deptId: String,
    onExitRequest: () -> Unit,
    onEvent: (TeacherListEvent)->Unit,
    ) {
    TopBarDecoratorCommon(
        topNavigationIcon = Icons.AutoMirrored.Default.ArrowBack,
        onNavigationIconClick = onExitRequest,
        topBarTitle = "Teacher List"
    ) {
        TeacherList(
            modifier = Modifier.padding(it),
            deptId = deptId,
            repository = FacultyComponentProvider.getTeacherListRepository(),
            onEvent=onEvent
        )
    }

}