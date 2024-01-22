package com.just.cse.digitaldiary.twozerotwothree.data.repository.faculty_list_repository.model

import java.util.UUID
import kotlin.random.Random

data class Department(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val shortName: String,
    val employeeCount: Int = Random.nextInt(5, 20)
)
