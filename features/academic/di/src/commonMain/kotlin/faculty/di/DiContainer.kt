package faculty.di

import data.DataFactory
import faculty.domain.usecase.admin.InsertDepartmentUseCase
import faculty.domain.usecase.admin.AddFacultyUseCase
import faculty.domain.usecase.admin.AddTeacherUseCase
import faculty.domain.usecase.admin.GetAllDepartmentUseCase
import faculty.domain.usecase.admin.GetFacultyByIdUseCase
import faculty.domain.usecase.admin.ReadDeptUseCase
import faculty.domain.usecase.admin.UpdateDepartmentUseCase
import faculty.domain.usecase.admin.UpdateFacultyUseCase
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
    fun getAllDeptUseCase()=GetAllDepartmentUseCase(_adminRepository())
    fun addTeacherUseCase() = AddTeacherUseCase(_adminRepository())
    fun readFacultyUseCase() = GetFacultyByIdUseCase(_adminRepository())
    fun updateFacultyUseCase()= UpdateFacultyUseCase(_adminRepository())
    private fun _adminRepository() = DataFactory.createAdminRepository()

}