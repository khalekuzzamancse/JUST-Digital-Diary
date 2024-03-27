package academic.data.department.source.local

import database.local.api.AcademicAPIs
import database.local.schema.academic.DepartmentEntityLocal
import faculty.domain.department.model.DepartmentListModel

class DepartmentLocalDataSourceImpl: DepartmentLocalDataSource {
    override suspend fun addDepartments(facultyId: String, entities: List<DepartmentListModel>) {
        val models = entities.map {
            DepartmentEntityLocal(
                id = it.id,
                deptId = it.deptId,
                employeeCount = it.employeeCount,
                name = it.name,
                shortname = it.shortName
            )
        }
        AcademicAPIs.addDepartments(facultyId, models)
    }

    override suspend fun getDepartments(facultyId: String): Result<List<DepartmentListModel>> {
        val localData: List<DepartmentListModel> = AcademicAPIs.retrieveDepartments(facultyId).getOrDefault(emptyList())
            .map { it.toModel() }
            .sortedBy { it.id }
//        println(localData)
        return Result.success(localData)
    }
}
private fun DepartmentEntityLocal.toModel() = DepartmentListModel(
    id = this.id,
    deptId = this.deptId,
    name = name,
    shortName = shortname,
    employeeCount = employeeCount

)