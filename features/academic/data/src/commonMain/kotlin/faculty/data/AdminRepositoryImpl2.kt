package faculty.data

import faculty.domain.model.DepartmentReadModel
import faculty.domain.model.DepartmentWriteModel
import faculty.domain.model.FacultyReadModel
import faculty.domain.model.FacultyWriteModel
import faculty.domain.model.TeacherReadModel
import faculty.domain.model.TeacherWriteModel
import faculty.domain.repository.AdminRepository

class AdminRepositoryImpl2:AdminRepository {
    override suspend fun insertFaculty(model: FacultyWriteModel): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun readFaculty(id: String): Result<FacultyReadModel> {
        TODO("Not yet implemented")
    }

    override suspend fun readDept(id: String): Result<DepartmentReadModel> {
        TODO("Not yet implemented")
    }

    override suspend fun insertDept(model: DepartmentWriteModel): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun readTeacher(id: String): Result<TeacherReadModel> {
        TODO("Not yet implemented")
    }

    override suspend fun insertTeacher(model: TeacherWriteModel): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllDept(): Result<List<DepartmentReadModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateFaculty(facultyId: String, model: FacultyWriteModel): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun updateDepartment(
        deptId: String,
        model: DepartmentWriteModel
    ): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun updateTeacher(teacherId: String, model: TeacherWriteModel): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFaculty(facultyId: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDepartment(deptId: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTeacher(teacherId: String): Result<Unit> {
        TODO("Not yet implemented")
    }
}