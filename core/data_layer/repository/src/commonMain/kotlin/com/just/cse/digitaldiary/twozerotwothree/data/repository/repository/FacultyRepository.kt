package com.just.cse.digitaldiary.twozerotwothree.data.repository.repository

import java.util.UUID
import kotlin.random.Random


//dept id=dept name science each dept name is unique
data class Faculty(
    val name: String,
    val id: String,
    val departments: List<Department> = emptyList()
)

data class FacultyInfo(
    val name: String,
    val id: String,
)


object FacultyRepository {

    val faculties = listOf(
        Faculty(
            name = "Applied Science And Technology",
            id ="01",
            departments = DepartmentFakeDB.departmentsOfAppliedScienceAndTechnology
        ),
        Faculty(
            name = "Biological Science And Technology",
            id = "02",
            departments = DepartmentFakeDB.departmentsOfBiologicalScienceAndTechnology
        ),
        Faculty(
            name = "Nursing And Health Science",
            id = "03",
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
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val shortName: String,
    val employeeCount: Int = Random.nextInt(5, 20)
)
