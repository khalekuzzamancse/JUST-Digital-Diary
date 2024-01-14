package com.just.cse.digital_diary.features.faculty.faculty.navigation

import com.just.cse.digital_diary.two_zero_two_three.auth.data.repository.SectionRepository



object Faculties{
    val facultyInfoList= SectionRepository
        .getSectionChild("FacultyMembers")

}
