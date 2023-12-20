package com.just.cse.digitaldiary.twozerotwothree.data.repository.department_employee_list_repoisitory.data

import com.just.cse.digitaldiary.twozerotwothree.core.network.employees.data.DepartmentEmployeeListFetcher
import com.just.cse.digitaldiary.twozerotwothree.data.repository.department_employee_list_repoisitory.model.Employee


object EmployeeRepository {
    private val employees = listOf(
        Employee(
            name = "Alice Smith",
            email = "alice@khulna.edu",
            additionalEmail = "alice.smith@gmail.com",
            profileImageLink = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRgacZ3AK1ch0QmvguIdB5kg-WJyb4Z0485zQqUP9lnoQy5wV-lglgCGECCFUgRpITrb6E&usqp=CAU",
            achievements = "B.Sc, M.Sc (IU, Bangladesh), PhD (Uleth, Canada)",
            phone = "+880123456789",
            designations = "Assistant Professor",
            deptName = "Computer Science ",
            deptSortName = "CS",
            roomNo = "A-101"
        ),
        Employee(
            name = "Bob Jones",
            email = "bob@khulna.edu",
            additionalEmail = "bob.jones@yahoo.com",
            profileImageLink = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRgacZ3AK1ch0QmvguIdB5kg-WJyb4Z0485zQqUP9lnoQy5wV-lglgCGECCFUgRpITrb6E&usqp=CAU",
            achievements = "B.Sc, M.Sc (IU, Bangladesh), PhD (Uleth, Canada)",
            phone = "+880987654321",
            designations = "Associate Professor",
            deptName = "Mathematics",
            deptSortName = "Math",
            roomNo = "B-202"
        ),
        Employee(
            name = "Charlie Brown",
            email = "charlie@khulna.edu",
            additionalEmail = "charlie.brown@hotmail.com",
            profileImageLink = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRgacZ3AK1ch0QmvguIdB5kg-WJyb4Z0485zQqUP9lnoQy5wV-lglgCGECCFUgRpITrb6E&usqp=CAU",
            achievements = "B.Sc, M.Sc (IU, Bangladesh), PhD (Uleth, Canada)",
            phone = "+880456789123",
            designations = "Lecturer",
            deptName = "Physics",
            deptSortName = "Phys",
            roomNo = "C-303"
        )
    )
    suspend fun  getEmployees(deptID: String):List<Employee>{
      return  DepartmentEmployeeListFetcher().fetch(deptID).map {
            Employee(
                name = it.name,
                email = it.email,
                additionalEmail = it.additional_email?:"",
                profileImageLink = it.profile_image?:"",
                achievements = it.achievement,
                phone = it.phone?:"",
                designations = it.designations,
                deptName = it.department_name,
                deptSortName = it.department_shortname,
                roomNo = it.room_no,
            )
        }

    }

    suspend fun  getStafff(deptID: String):List<Employee>{
        return  employees
        }

    }




