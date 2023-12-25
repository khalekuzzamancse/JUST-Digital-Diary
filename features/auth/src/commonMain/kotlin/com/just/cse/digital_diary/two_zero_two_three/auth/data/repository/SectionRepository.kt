package com.just.cse.digital_diary.two_zero_two_three.auth.data.repository

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.UUID

data class SectionInfo(
    val id: String,
    val name: String,
    val logo: ImageVector = Icons.Default.School
)

data class Section(
    val id: String,
    val name: String,
    val logo: ImageVector = Icons.Default.School,
    val faculties: List<FacultyInfo>,
)

object SectionRepository {
    private val sections = listOf(
        Section(
            id = UUID.randomUUID().toString(),
            name = "Faculty Members",
            faculties = FacultyRepository.getFacultyInfoList()
        ),
        Section(
            id = UUID.randomUUID().toString(),
            name = "Administrators",
            faculties = emptyList()
        )
    )
    val sectionInfo: List<SectionInfo>
        get() {
            return sections.map {
                SectionInfo(
                    id = it.id,
                    name = it.name,
                    logo = it.logo
                )
            }
        }

    fun getFacultiesInfo(index: Int): List<FacultyInfo> {
        val faculties = sections.getOrNull(index)?.faculties
        return faculties?.map {
            FacultyInfo(id = it.id, name = it.name, logo = it.logo)
        } ?: emptyList()
    }


    fun getFacultyId(sectionIndex: Int, facultyIndex: Int): String {
        val section = getSection(sectionIndex)
        if (section != null) {
            val facultyInfo = section.faculties.getOrNull(facultyIndex)
            if (facultyInfo != null) {
                return facultyInfo.id
            }
        }
        return ""
    }


    fun getSection(sectionIndex: Int) = sections.getOrNull(sectionIndex)
    fun getSectionInfo(index: Int): SectionInfo? {
        val section = getSection(index)
        if (section != null) {
            return SectionInfo(
                id = section.id,
                name = section.name,
                logo = section.logo
            )
        }
        return null

    }

    fun getSection(id:String):SectionInfo? {
       val section  =sections.find { it.id==id }
        if(section != null) {
           return SectionInfo(
                id = section.id,
                name = section.name,
                logo = section.logo
            )
        }
        return null
    }

    fun getSectionChild(sectionId: String): List<FacultyInfo> {
        val section = sections.find { it.id == sectionId }
        if (section != null) {
            return section.faculties
        }
        return emptyList()

    }
}

