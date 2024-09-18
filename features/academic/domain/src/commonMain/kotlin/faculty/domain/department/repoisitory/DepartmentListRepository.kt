package faculty.domain.department.repoisitory

import faculty.domain.department.model.DepartmentListModel

interface DepartmentListRepository{
    suspend fun getDepartment(facultyId:String): Result<List<DepartmentListModel>>

}