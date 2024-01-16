package com.just.cse.digitaldiary.twozerotwothree.data.repository.repository

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





