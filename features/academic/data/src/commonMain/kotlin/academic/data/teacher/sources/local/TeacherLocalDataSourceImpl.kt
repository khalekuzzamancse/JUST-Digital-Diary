package academic.data.teacher.sources.local

import database.local.api.AcademicAPIs
import database.local.schema.academic.DesignationEntityLocal
import database.local.schema.academic.TeacherEntityLocal
import faculty.domain.teacher.model.TeacherModel
import java.util.UUID

class TeacherLocalDataSourceImpl: TeacherLocalDataSource {
    override suspend fun getTeachers(deptId: String): Result<List<TeacherModel>> {
        val localData: List<TeacherModel> = AcademicAPIs
            .retrieveTeachers(deptId)
            .getOrDefault(emptyList())
            .map { it.toModel() }
            .sortedBy { it.id }
        println(localData)
        return Result.success(localData)
    }

    override suspend fun addTeachers(deptId: String, entities: List<TeacherModel>) {
        val models = entities.map {
            TeacherEntityLocal(
                uid = UUID.randomUUID().toString(),
                name = it.name,
                email = it.email,
                achievement = it.achievements,
                additionalEmail = it.additionalEmail,
                phone = it.phone,
                profileImage = it.profileImageLink,
                roomNo = it.roomNo,
                designations = listOf(DesignationEntityLocal(it.designations)),
                deptId = deptId,
                departmentName = it.deptName,
                shortName = it.deptSortName,
                id = it.id
            )
        }
        AcademicAPIs.addTeachers(models)
    }
}

private fun TeacherEntityLocal.toModel()=   TeacherModel(
    id=this.id,
    name = this.name,
    email = this.email,
    additionalEmail = this.additionalEmail ?: "",
    profileImageLink = this.profileImage ?: "",
    achievements = this.achievement,
    phone = this.phone ?: "",
    designations = this.designations.toString(),
    deptName = this.departmentName,
    deptSortName = this.shortName,
    roomNo = this.roomNo
)