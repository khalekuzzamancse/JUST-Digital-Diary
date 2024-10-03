@file:Suppress("SpellChecking")
package data

import data.entity.DepartmentListEntity
import data.entity.FacultyEntity
import data.entity.TeacherEntity
import data.entity.TeacherListEntity
import faculty.domain.model.DepartmentModel
import faculty.domain.model.DepartmentSubModel
import faculty.domain.model.FacultyModel
import faculty.domain.model.TeacherModel
import java.lang.Integer.min

internal object Mapper {
    fun toFacultyModel(entities: List<FacultyEntity>): List<FacultyModel> {
        return entities.map {
            FacultyModel(
                facultyId = it.facultyId,
                name = it.name,
                departmentsCount = it.departmentCount,
                id = it.id,
            )

        }
    }

    fun toDeptModels(entity: DepartmentListEntity): List<DepartmentModel> {
        return entity.departments.map {
            DepartmentModel(
                id = it.id,
                deptId = it.deptId,
                name = it.name,
                shortName = it.shortName,
                employeeCount = it.membersCount
            )

        }
    }
    fun  toTeacherModels(entity: TeacherListEntity): List<TeacherModel> {
        val sorter = DesignationSorter()
        return sorter.sortTeachers(entity.facultyMembers).map {
            TeacherModel(
                name = it.name,
                email = it.email,
                additionalEmail = it.additional_email,
                phone = it.phone,
                departments = it.departments.map { dept->
                    DepartmentSubModel(
                        name = dept.name,
                        designation = dept.designation,
                        roomNo = dept.room_no,
                        present = dept.present,
                        shortname = dept.shortname
                    )
                },
                role = it.role,
                achievement = it.achievement,
                profile = it.profile,
                type = it.type,
                uid = it.uid,
            )
        }
    }
}
internal class DesignationSorter {
    // A predefined map that ranks the designations
    private val rankMap = mapOf(
        "chairman" to 1,
        "professor" to 2,
        "associateprofessor" to 3,
        "assistantprofessor" to 4,
        "lecturer" to 5
    )

    // Function to normalize the input (remove spaces, ignore cases)
    private fun normalize(designation: String): String {
        return designation.replace("\\s".toRegex(), "").lowercase()
    }

    // Levenshtein distance algorithm to calculate the difference between two strings
    private fun levenshteinDistance(lhs: String, rhs: String): Int {
        val lhsLength = lhs.length
        val rhsLength = rhs.length
        val dp = Array(lhsLength + 1) { IntArray(rhsLength + 1) }

        for (i in 0..lhsLength) {
            for (j in 0..rhsLength) {
                when {
                    i == 0 -> dp[i][j] = j
                    j == 0 -> dp[i][j] = i
                    lhs[i - 1] == rhs[j - 1] -> dp[i][j] = dp[i - 1][j - 1]
                    else -> dp[i][j] = 1 + min(
                        min(dp[i - 1][j], dp[i][j - 1]),
                        dp[i - 1][j - 1]
                    )
                }
            }
        }
        return dp[lhsLength][rhsLength]
    }

    // Function to find the closest matching designation based on similarity
    private fun findClosestMatch(designation: String): String {
        val threshold = 3 // You can tweak this threshold to make it more or less lenient
        val normalizedDesignation = normalize(designation)
        var closestMatch = "other"
        var minDistance = Int.MAX_VALUE

        for (entry in rankMap.keys) {
            val distance = levenshteinDistance(normalizedDesignation, entry)
            if (distance < minDistance && distance <= threshold) {
                minDistance = distance
                closestMatch = entry
            }
        }
        return closestMatch
    }

    // Function to get the rank of the designation, assigning a higher number to "other"
    private fun getRank(designation: String): Int {
        val closestMatch = findClosestMatch(designation)
        return rankMap[closestMatch] ?: 6 // Default rank for "other"
    }

    // Sorting function that takes a list of TeacherEntity and sorts them based on the first department's designation
    fun sortTeachers(teachers: List<TeacherEntity>): List<TeacherEntity> {
        return teachers.sortedBy {
            // Get the designation of the first department or empty string if no departments
            val firstDesignation = it.departments.firstOrNull()?.designation ?: ""
            getRank(firstDesignation)
        }
    }
}