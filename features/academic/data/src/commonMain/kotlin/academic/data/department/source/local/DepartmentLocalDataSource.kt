package academic.data.department.source.local

import faculty.domain.department.model.DepartmentListModel

internal interface DepartmentLocalDataSource {
    suspend fun addDepartments(facultyId: String, entities: List<DepartmentListModel>)
    suspend fun getDepartments(facultyId: String): Result<List<DepartmentListModel>>
}