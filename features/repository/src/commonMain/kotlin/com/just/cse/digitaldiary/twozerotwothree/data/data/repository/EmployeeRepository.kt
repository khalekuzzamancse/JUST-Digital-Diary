package com.just.cse.digitaldiary.twozerotwothree.data.data.repository

data class Employee(
    val name: String,
    val email: String,
    val additionalEmail: String,
    val profileImageLink: String,
    val achievements: String,
    val phone: String,
    val designations: String,
    val deptName: String,
    val deptSortName: String,
    val roomName: String,
)




val employees = listOf(
    Employee(
        name = "Alice Smith",
        email = "alice@khulna.edu",
        additionalEmail = "alice.smith@gmail.com",
        profileImageLink = "https://i.imgur.com/1.jpg",
        achievements = "Best Teacher Award 2022",
        phone = "+880123456789",
        designations = "Assistant Professor",
        deptName = "Computer Science",
        deptSortName = "CS",
        roomName = "A-101"
    ),
    Employee(
        name = "Bob Jones",
        email = "bob@khulna.edu",
        additionalEmail = "bob.jones@yahoo.com",
        profileImageLink = "https://i.imgur.com/2.jpg",
        achievements = "Best Research Paper 2023",
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
        profileImageLink = "https://i.imgur.com/3.jpg",
        achievements = "Best Student Mentor 2021",
        phone = "+880456789123",
        designations = "Lecturer",
        deptName = "Physics",
        deptSortName = "Phys",
        roomName = "C-303"
    )
)


fun generateDummyEmployeeList(numberOfEmployees: Int): List<Employee> {
    val employeeNames = listOf("John", "Jane", "Bob", "Alice", "Eva", "David", "Emily", "Michael", "Olivia", "Daniel")
    val departmentNames = listOf("IT", "HR", "Marketing", "Finance", "Engineering", "Sales")

    return List(numberOfEmployees) {
        Employee(
            name = employeeNames.random(),
            email = "example${it + 1}@company.com",
            additionalEmail = "additional${it + 1}@company.com",
            profileImageLink = "https://example.com/image${it + 1}.png",
            achievements = "Achievements for Employee $it",
            phone = "+1 555-1234-${String.format("%04d", it)}",
            designations = "Designation $it",
            deptName = departmentNames.random(),
            deptSortName = "DeptSort$it",
            roomName = "Room$it"
        )
    }
}



