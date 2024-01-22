package com.just.cse.digitaldiary.twozerotwothree.data.repository.faculty_list_repository.data

import com.just.cse.digitaldiary.twozerotwothree.data.repository.faculty_list_repository.model.Faculty
import java.util.UUID

object FacultyListRepository {
    private val faculties = listOf(
        Faculty(
            name = "Applied Science And Technology",
            id ="01",
            departmentCnt = 7,
        ),
        Faculty(
            name = "Biological Science And Technology",
            id = "02",
            departmentCnt =4,
        ),
        Faculty(
            name = "Nursing And Health Science",
            id = "03",
            departmentCnt = 12,
        ),
        Faculty(
            name = "English",
            id = UUID.randomUUID().toString(),
            departmentCnt =1,
        ),
        Faculty(
            name = "Science",
            id = UUID.randomUUID().toString(),
            departmentCnt =12
        ),
        Faculty(
            name = "Business And Finance",
            id = UUID.randomUUID().toString()
        ),
        Faculty(
            name = "Engineering And Technology",
            id = UUID.randomUUID().toString(),
            departmentCnt = 10
        )

    )

   suspend fun getFacultyInfoList(): List<Faculty> {
        return faculties
    }


}
