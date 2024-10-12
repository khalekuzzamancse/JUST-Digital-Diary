package faculty.di

import data.DataFactory
import faculty.domain.usecase.admin.AddDepartmentUseCase
import faculty.domain.usecase.admin.AddFacultyUseCase
import faculty.domain.usecase.admin.AddTeacherUseCase
import faculty.domain.usecase.admin.GetAllDepartmentUseCase
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
    fun addDepartmentUseCase() = AddDepartmentUseCase(_adminRepository())
    fun getAllDeptUseCase()=GetAllDepartmentUseCase(_adminRepository())
    fun addTeacherUseCase() = AddTeacherUseCase(_adminRepository())
    private fun _adminRepository() = DataFactory.createAdminRepository()

}