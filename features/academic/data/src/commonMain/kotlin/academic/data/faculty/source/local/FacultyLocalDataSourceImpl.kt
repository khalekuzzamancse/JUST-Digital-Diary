package academic.data.faculty.source.local

import database.local.api.AcademicAPIs
import database.local.schema.academic.FacultyEntityLocal
import faculty.domain.faculties.model.FacultyInfoModel

internal class FacultyLocalDataSourceImpl : FacultyLocalDataSource {
    override suspend fun addFaculties(entities: List<FacultyInfoModel>) {
        val models = entities.map {
            FacultyEntityLocal(
                id = it.id,
                facultyId = it.facultyId,
                name = it.name,
                deptCount = it.departmentsCount,
            )
        }
        AcademicAPIs.addFaculties(models)
    }

    override suspend fun getFaculties(): Result<List<FacultyInfoModel>> {
        val localData: List<FacultyInfoModel> = AcademicAPIs
            .retrieveFaculties()
            .getOrDefault(emptyList()).map { it.toModel() }
            .sortedBy { it.id }
//        println(localData)
        return Result.success(localData)
    }


}

private fun FacultyEntityLocal.toModel() = FacultyInfoModel(
    id = id,
    name = name,
    facultyId = facultyId,
    departmentsCount = deptCount
)