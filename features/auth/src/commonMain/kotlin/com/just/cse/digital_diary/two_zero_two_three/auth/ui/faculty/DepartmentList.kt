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
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlin.random.Random




//dept id=dept name science each dept name is unique
data class Department(
    val fullName: String,
    val shortName: String,
    val employeeCount: Int = Random.nextInt(5, 20),
    val logo: ImageVector? = null,
    val icon: ImageVector = Icons.Default.School
)
data class Department2(
    val fullName: String,
    val shortName: String,
    val employeeCount: Int = Random.nextInt(5, 20),
    val icon: ImageVector = Icons.Default.School
)
val departmentsOfAppliedScienceAndTechnology = listOf(
    Department2(
        fullName = "Department Of Agro Product Processing Technology",
        shortName = "Agro Processing"
    ),
    Department2(
        fullName = "Department Of Climate and Disaster Management",
        shortName = "Climate & Disaster Management"
    ),
    Department2(
        fullName = "Department Of Environmental Science and Technology",
        shortName = "Environmental Science & Tech"
    ),
    Department2(
        fullName = "Department Of Nutrition and Food Technology",
        shortName = "Nutrition & Food Tech"
    )
)
val departmentsOfBiologicalScienceAndTechnology = listOf(
    Department2(
        fullName = "Department Of Fisheries and Marine Bioscience",
        shortName = "Fisheries & Marine Bioscience"
    ),
    Department2(
        fullName = "Department Of Genetic Engineering and Biotechnology",
        shortName = "Genetic Engineering & Biotechnology"
    ),
    Department2(
        fullName = "Department Of Microbiology",
        shortName = "Microbiology"
    ),
    Department2(
        fullName = "Department Of Pharmacy",
        shortName = "Pharmacy"
    )
)
val departmentsOfNursingAndHealthScience = listOf(
    Department2(
        fullName = "Department Of Nursing and Health Science",
        shortName = "Nursing & Health Science"
    ),
    Department2(
        fullName = "Department Of Physical Education and Sports Science",
        shortName = "Physical Education & Sports Science"
    ),
    Department2(
        fullName = "Department Of Physiotherapy and Rehabilitation",
        shortName = "Physiotherapy & Rehabilitation"
    )
)
val departmentOfEnglish = listOf(
    Department2(
        fullName = "Department Of English",
        shortName = "English"
    )
)
val departmentsOfScience = listOf(
    Department2(
        fullName = "Department Of Chemistry",
        shortName = "Chemistry"
    ),
    Department2(
        fullName = "Department Of Mathematics",
        shortName = "Mathematics"
    ),
    Department2(
        fullName = "Department Of Physics",
        shortName = "Physics"
    )
)
val departmentsOfBusinessAndFinance = listOf(
    Department2(
        fullName = "Department Of Accounting and Information Systems",
        shortName = "Accounting & Information Systems"
    ),
    Department2(
        fullName = "Department Of Finance and Banking",
        shortName = "Finance & Banking"
    ),
    Department2(
        fullName = "Department Of Management",
        shortName = "Management"
    ),
    Department2(
        fullName = "Department Of Marketing",
        shortName = "Marketing"
    )
)
val departmentsOfEngineeringAndTechnology = listOf(
    Department2(
        fullName = "Department Of Biomedical Engineering",
        shortName = "Biomedical Engineering"
    ),
    Department2(
        fullName = "Department Of Chemical Engineering",
        shortName = "Chemical Engineering"
    ),
    Department2(
        fullName = "Department Of Computer Science and Engineering",
        shortName = "Computer Science & Engineering"
    ),
    Department2(
        fullName = "Department Of Electrical and Electronic Engineering",
        shortName = "Electrical & Electronic Engineering"
    ),
    Department2(
        fullName = "Department Of Industrial and Production Engineering",
        shortName = "Industrial & Production Engineering"
    ),
    Department2(
        fullName = "Department Of Petroleum and Mining Engineering",
        shortName = "Petroleum & Mining Engineering"
    ),
    Department2(
        fullName = "Department Of Textile Engineering",
        shortName = "Textile Engineering"
    )
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
    DepartmentList(modifier = Modifier.width(400.dp), depts)
}

@Composable
fun DepartmentList(
    modifier: Modifier,
    departments: List<Department>
) {
    GenericListScreen(
        modifier = modifier,
        items = departments
    ) { mod, value ->
        DepartmentCard(
            modifier = mod.padding(8.dp).fillMaxWidth(),
            department = value,
            showFullName = true
        )
    }


}
@Composable
fun DepartmentList2(
    modifier: Modifier,
    departments: List<Department2>
) {
    GenericListScreen(
        modifier = modifier,
        items = departments
    ) { mod, value ->
        DepartmentCard(
            modifier = mod.padding(8.dp).fillMaxWidth(),
            fullName=value.fullName,
            shortName=value.shortName,
            showFullName= true,
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
fun DepartmentCard(
    modifier: Modifier,
    fullName: String,
    shortName: String,
    showFullName: Boolean = false,
    employeeCount: Int = Random.nextInt(5, 20),
    icon: ImageVector = Icons.Default.School
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
                text = if (showFullName) fullName else shortName,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(4.dp))
            Row(modifier = Modifier) {
                Icon(imageVector = Icons.Default.Person4, null)
                Spacer(Modifier.width(4.dp))
                Text(text = "$employeeCount Teacher(s)")
            }
        }
    }
}


@Composable
fun <T> GenericListScreen(
    modifier: Modifier = Modifier,
    items: List<T>,
    itemContent: @Composable (Modifier, T) -> Unit,
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
