package com.just.cse.digitaldiary.twozerotwothree.data.repository.employee_list_repoisitory.data

import com.just.cse.digitaldiary.twozerotwothree.data.repository.employee_list_repoisitory.model.Employee


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
            roomName = "A-101"
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
            roomName = "B-202"
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
            roomName = "C-303"
        )
    )
    suspend fun  getEmployees(deptID: String):List<Employee>{
        return employees
    }



}


