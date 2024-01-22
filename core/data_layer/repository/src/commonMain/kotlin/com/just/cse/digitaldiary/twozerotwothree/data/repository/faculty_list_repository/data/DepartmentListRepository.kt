package com.just.cse.digitaldiary.twozerotwothree.data.repository.faculty_list_repository.data

import com.just.cse.digitaldiary.twozerotwothree.core.network.deparment_list.data.DepartmentListFetcher
import com.just.cse.digitaldiary.twozerotwothree.data.repository.faculty_list_repository.model.Department

object DepartmentListRepository {

    private val departmentsOfEngineeringAndTechnology = listOf(
        Department(
            name = "Department Of Biomedical Engineering",
            shortName = "BME",

        ),
        Department(
            name = "Department Of Chemical Engineering",
            shortName = "ChE"
        ),
        Department(
            name = "Department Of Computer Science and Engineering",
            shortName = "CSE"
        ),
        Department(
            name = "Department Of Electrical and Electronic Engineering",
            shortName = "EEE"
        ),
        Department(
            name = "Department Of Industrial and Production Engineering",
            shortName = "IPE"
        ),
        Department(
            name = "Department Of Petroleum and Mining Engineering",
            shortName = "PME"
        ),
        Department(
            name = "Department Of Textile Engineering",
            shortName = "TE"
        )
    )
    suspend fun getDepartments(facultyId: String):List<Department> {
        println("getDepartmentsRequest:$facultyId")
        val res= DepartmentListFetcher().fetch(facultyId)
        println("getDepartmentsRequest:$res")
        return res.map {
            Department(
                id=it.dept_id,
                name = it.name,
                employeeCount = it.membersCount,
                shortName = it.shortname
            )
        }
    }

}