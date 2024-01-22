package com.just.cse.digitaldiary.twozerotwothree.data.repository.employee_list_repoisitory.model

data class Employee(
    val name: String,
    val email: String,
    val additionalEmail: String,
    val profileImageLink: String="https://static.just.edu.bd/images/public/teachers/1603270274986_900.jpeg",
    val achievements: String,
    val phone: String,
    val designations: String,
    val deptName: String,
    val deptSortName: String,
    val roomName: String,
)