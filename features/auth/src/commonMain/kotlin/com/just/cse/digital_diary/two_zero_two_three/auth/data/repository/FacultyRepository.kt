package com.just.cse.digital_diary.two_zero_two_three.auth.data.repository

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Science
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.UUID
import kotlin.random.Random


//dept id=dept name science each dept name is unique
data class Faculty(
    val name: String,
    val id: String,
    val logo: ImageVector = Icons.Default.Science,
    val departments: List<Department> = emptyList()
)

data class FacultyInfo(
    val name: String,
    val id: String,
    val logo: ImageVector = Icons.Default.Science,
)


object FacultyRepository {

    val faculties = listOf(
        Faculty(
            name = "Applied Science And Technology",
            id = UUID.randomUUID().toString(),
            departments = DepartmentFakeDB.departmentsOfAppliedScienceAndTechnology
        ),
        Faculty(
            name = "Biological Science And Technology",
            id = UUID.randomUUID().toString(),
            departments = DepartmentFakeDB.departmentsOfBiologicalScienceAndTechnology
        ),
        Faculty(
            name = "Nursing And Health Science",
            id = UUID.randomUUID().toString(),
            departments = DepartmentFakeDB.departmentsOfNursingAndHealthScience
        ),
        Faculty(
            name = "English",
            id = UUID.randomUUID().toString(),
            departments = DepartmentFakeDB.departmentOfEnglish
        ),
        Faculty(
            name = "Science",
            id = UUID.randomUUID().toString(),
            departments = DepartmentFakeDB.departmentsOfScience
        ),
        Faculty(
            name = "Business And Finance",
            id = UUID.randomUUID().toString(),
            departments = DepartmentFakeDB.departmentsOfBusinessAndFinance
        ),
        Faculty(
            name = "Engineering And Technology",
            id = UUID.randomUUID().toString(),
            departments = DepartmentFakeDB.departmentsOfEngineeringAndTechnology
        )

    )

    fun getFacultyInfoList(): List<FacultyInfo> {
        return faculties.map { FacultyInfo(name = it.name, id = it.id) }
    }

    fun getDepartments(facultyId: String): List<Department> {
        val faculty = faculties.find { it.id == facultyId }
        return faculty?.departments ?: emptyList()
    }
    fun getDepartmentById(departmentId: String): Department? {
        return faculties
            .flatMap { it.departments }
            .find { it.id == departmentId }
    }
}


data class Department(
    val id:String=UUID.randomUUID().toString(),
    val fullName: String,
    val shortName: String,
    val employeeCount: Int = Random.nextInt(5, 20),
    val logo: ImageVector = Icons.Default.School
)
