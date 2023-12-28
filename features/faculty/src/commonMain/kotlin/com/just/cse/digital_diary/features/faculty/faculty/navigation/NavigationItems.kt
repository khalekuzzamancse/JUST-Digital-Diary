package com.just.cse.digital_diary.features.faculty.faculty.navigation
import com.just.cse.digital_diary.features.common_ui.navigation.NavigationItem
import com.just.cse.digital_diary.two_zero_two_three.auth.data.repository.SectionRepository

val destinations=SectionRepository
    .getSectionChild("FacultyMembers")
    .map {
        NavigationItem(
            label = it.name,
            unFocusedIcon = it.logo,
            key = it.id
        )
    }
