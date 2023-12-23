package com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person4
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlin.random.Random

data class SectionType(
    val sectionId: Int,//finder  to ask for data using it
    val name: String,
    val logo:ImageVector
)
data class SectionCategory(
    val id: Int,//finder  to ask for data using it
    val name: String,
    val logo:ImageVector
)
data class EmployeeSection(
    val name: String,
    val departments: List<Department>,
    val logo:ImageVector?=null
)


//dept id=dept name science each dept name is unique
data class Department(
    val fullName: String,
    val shortName: String,
    val employeeCount: Int = Random.nextInt(5, 20),
    val logo: ImageVector?=null,
)

val depts = listOf(
    Department("Computer Science and Engineering", "CSE"),
    Department("Industrial and Production Engineering", "IPE"),
    Department("Mechanical Engineering", "PME"),
    Department("Chemical Engineering", "ChE"),
    Department("Electrical and Electronic Engineering", "EEE"),
    Department("Biomedical Engineering", "BME"),
    Department("Textile Engineering", "TE")
)


@Composable
fun DepartmentListDemo() {
    DepartmentList(modifier = Modifier.width(400.dp),depts)
}

@Composable
fun DepartmentList(
    modifier: Modifier,
    departments: List<Department>
) {
        GenericListScreen(
            modifier = modifier,
            items = departments
        ) {mod,value->
            DepartmentCard(
                modifier = mod.padding(8.dp).fillMaxWidth(),
                department = value,
                showFullName = true
            )
        }



}

@Composable
fun DepartmentCard(
    modifier: Modifier,
    department: Department,
    showFullName: Boolean = false
) {
    Surface(
        modifier = modifier,
        shadowElevation = 8.dp,
        onClick = {}
    ) {
        Column(
            modifier = Modifier
        ) {
            Text(
                text = if (showFullName) department.fullName else department.shortName,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(4.dp))
            Row(modifier = Modifier) {
                Icon(imageVector = Icons.Default.Person4, null)
                Spacer(Modifier.width(4.dp))
                Text(text = "${department.employeeCount} Teacher(s)")
            }
        }
    }
}

@Composable
fun <T> GenericListScreen(
    modifier: Modifier = Modifier,
    items: List<T>,
    itemContent: @Composable (Modifier,T) -> Unit,
) {
    // Use a Box to place an invisible Text with the longest name
    // This will determine the width of the LazyColumn

        LazyColumn(
            modifier = modifier,
        ) {
            items(items) { item ->
                itemContent(Modifier, item)
            }
        }


}
