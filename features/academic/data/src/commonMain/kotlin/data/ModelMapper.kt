@file:Suppress("SpellCheckingInspection")

package data

import data.entity.admin.DepartmentEntryEntity
import data.entity.admin.FacultyEntryEntity
import data.entity.admin.TeacherEntryEntity
import data.entity.public_.DepartmentListEntity
import data.entity.public_.FacultyEntity
import data.entity.public_.TeacherEntity
import data.entity.public_.TeacherListEntity
import faculty.domain.model.admin.DepartmentEntryModel
import faculty.domain.model.admin.FacultyEntryModel
import faculty.domain.model.admin.TeacherEntryModel
import faculty.domain.model.public_.DepartmentModel
import faculty.domain.model.public_.FacultyModel
import faculty.domain.model.public_.TeacherModel
import java.lang.Integer.min

internal object ModelMapper {
    fun toFacultyModel(entities: List<FacultyEntity>): List<FacultyModel> {
        return entities.map {
            FacultyModel(
                facultyId = it.facultyId,
                name = it.name,
                departmentsCount = it.departmentCount,
            )

        }
    }

    fun FacultyEntryEntity.toModel() =
        FacultyModel(
            facultyId = facultyId,
            name = name,
            departmentsCount = numberOfDept,
        )

    fun FacultyEntryEntity.toEntryModel() =
        FacultyEntryModel(
            priority = priority,
            name = name,
        )

    fun toDeptModels(entity: DepartmentListEntity): List<DepartmentModel> {
        return entity.departments.map {
            DepartmentModel(
                deptId = it.deptId,
                name = it.name,
                shortname = it.shortName,
                employeeCount = it.membersCount
            )

        }
    }

    /**
     * - Assign a dummy value for the facultyId
     * - Used when know the facultyId such as read by faculty id, or read by under faculty or read by dept id
     * -
     */
    fun DepartmentEntryEntity.toEntryModel()=DepartmentEntryModel(
        name = name,
        shortname = shortname,
        priority = priority,
        facultyId = "assigned dummy value at mapper "
    )

    fun toTeacherModels(entity: TeacherListEntity): List<TeacherModel> {
        val sorter = DesignationSorter()
        return sorter.sortTeachers(entity.facultyMembers).map {
            val dept = it.departments.firstOrNull()
            TeacherModel(
                name = it.name,
                email = it.email,
                additionalEmail = it.additional_email,
                achievements = it.achievement,
                phone = it.phone,
                designations = dept?.designation ?: "",
                roomNo = dept?.room_no ?: "",
                profile = it.profile,
                id = it.uid,
            )
        }
    }

    fun toFacultyEntryEntity(model: FacultyEntryModel) = FacultyEntryEntity(
        priority = model.priority,
        name = model.name,
    )

    fun toDepartmentEntryEntity(model: DepartmentEntryModel) = DepartmentEntryEntity(
        priority = model.priority,
        name = model.name,
        shortname = model.shortname,

        )

    fun convertModelToEntity(model: TeacherEntryModel): TeacherEntryEntity {
        return TeacherEntryEntity(
            deptId = model.deptId,
            priority = model.priority,
            name = model.name,
            email = model.email,
            additionalEmail = model.additionalEmail,
            achievements = model.achievements,
            phone = model.phone,
            designations = model.designations,
            roomNo = model.roomNo,
            profileImageLink = model.profileImageLink
        )
    }

    fun FacultyEntryModel.toEntity() = FacultyEntryEntity(
        priority = this.priority,
        name = this.name,
    )

    fun DepartmentEntryModel.toEntity() = DepartmentEntryEntity(
        priority = this.priority,
        name = this.name,
        shortname = this.shortname,
    )

    fun TeacherEntryModel.toEntity() = TeacherEntryEntity(
        deptId = deptId,
        priority = priority,
        name = name,
        email = email,
        additionalEmail = additionalEmail?.ifBlank { null },
        achievements = achievements,
        phone = phone,
        designations = designations,
        roomNo = roomNo?.ifBlank { null },
        profileImageLink = profileImageLink
    )

    fun TeacherEntryEntity.toModel() = TeacherModel(
        id = id,
        name = name,
        email = email,
        additionalEmail = additionalEmail?.ifBlank { null },
        achievements = achievements,
        phone = phone,
        designations = designations,
        roomNo = roomNo,
        profile = profileImageLink
    )
    fun TeacherEntryEntity.toEntryModel() = TeacherEntryModel(
        deptId=deptId,
        priority = priority,
        name = name,
        email = email,
        additionalEmail = additionalEmail?.ifBlank { null },
        achievements = achievements,
        phone = phone,
        designations = designations,
        roomNo = roomNo,
        profileImageLink = profileImageLink
    )

    fun DepartmentEntryEntity.toModel() = DepartmentModel(
        deptId = deptId,
        name = name,
        shortname = shortname,
        employeeCount = numOfEmployee
    )
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