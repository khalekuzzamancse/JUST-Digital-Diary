package faculty.di

import data.DataFactory
import faculty.domain.usecase.admin.InsertDepartmentUseCase
import faculty.domain.usecase.admin.AddFacultyUseCase
import faculty.domain.usecase.admin.InsertTeacherUseCase
import faculty.domain.usecase.admin.ReadAllDepartmentUseCase
import faculty.domain.usecase.admin.GetFacultyByIdUseCase
import faculty.domain.usecase.admin.ReadDeptUseCase
import faculty.domain.usecase.admin.ReadTeacherUseCase
import faculty.domain.usecase.admin.UpdateDepartmentUseCase
import faculty.domain.usecase.admin.UpdateFacultyUseCase
import faculty.domain.usecase.admin.UpdateTeacherUseCase
import faculty.domain.usecase.public_.RetrieveDepartmentsUseCase
import faculty.domain.usecase.public_.RetrieveFactualityUseCase
import faculty.domain.usecase.public_.RetrieveTeachersUseCase


object DiContainer {

    fun retrieveFacultyListUseCase(token: String?): RetrieveFactualityUseCase =
        RetrieveFactualityUseCase(
            repository = DataFactory.createPublicRepository(token)
        )

    fun retrieveDepartListUseCase(token: String?): RetrieveDepartmentsUseCase =
        RetrieveDepartmentsUseCase(
            repository = DataFactory.createPublicRepository(token)
        )

    fun retrieveTeacherListUseCase(token: String?): RetrieveTeachersUseCase =
        RetrieveTeachersUseCase(
            repository = DataFactory.createPublicRepository(token)
        )

    fun addFacultyUseCase() = AddFacultyUseCase(_adminRepository())
    fun readDeptUseCase() = ReadDeptUseCase(_adminRepository())
    fun insertDeptUseCase() = InsertDepartmentUseCase(_adminRepository())
    fun updateDeptUseCase() = UpdateDepartmentUseCase(_adminRepository())
    fun getAllDeptUseCase()=ReadAllDepartmentUseCase(_adminRepository())
    fun readTeacherUseCase() = ReadTeacherUseCase(_adminRepository())
    fun insertTeacherUseCase() = InsertTeacherUseCase(_adminRepository())
    fun updateTeacherUseCase() = UpdateTeacherUseCase(_adminRepository())
    fun readFacultyUseCase() = GetFacultyByIdUseCase(_adminRepository())
    fun updateFacultyUseCase()= UpdateFacultyUseCase(_adminRepository())
    private fun _adminRepository() = DataFactory.createAdminRepository()

}