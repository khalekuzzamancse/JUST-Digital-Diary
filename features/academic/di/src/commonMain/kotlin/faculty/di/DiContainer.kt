@file:Suppress("functionName")
package faculty.di

import data.DataFactory
import faculty.domain.usecase.admin.InsertDepartmentUseCase
import faculty.domain.usecase.admin.InsertFacultyUseCase
import faculty.domain.usecase.admin.InsertTeacherUseCase
import faculty.domain.usecase.admin.ReadAllDepartmentUseCase
import faculty.domain.usecase.admin.ReadFacultyUseCase
import faculty.domain.usecase.admin.ReadDeptUseCase
import faculty.domain.usecase.admin.ReadTeacherUseCase
import faculty.domain.usecase.admin.UpdateDepartmentUseCase
import faculty.domain.usecase.admin.UpdateFacultyUseCase
import faculty.domain.usecase.admin.UpdateTeacherUseCase
import faculty.domain.usecase.public_.ReadDepartmentsUseCase
import faculty.domain.usecase.public_.ReadAllFactualityUseCase
import faculty.domain.usecase.public_.ReadTeachersUseCase


object DiContainer {

    fun retrieveFacultyListUseCase(token: String?): ReadAllFactualityUseCase =
        ReadAllFactualityUseCase(
            repository = DataFactory.createPublicRepository(token)
        )

    fun retrieveDepartListUseCase(token: String?): ReadDepartmentsUseCase =
        ReadDepartmentsUseCase(
            repository = DataFactory.createPublicRepository(token)
        )

    fun retrieveTeacherListUseCase(token: String?): ReadTeachersUseCase =
        ReadTeachersUseCase(
            repository = DataFactory.createPublicRepository(token)
        )

    fun addFacultyUseCase() = InsertFacultyUseCase(_adminRepository())
    fun readDeptUseCase() = ReadDeptUseCase(_adminRepository())
    fun insertDeptUseCase() = InsertDepartmentUseCase(_adminRepository())
    fun updateDeptUseCase() = UpdateDepartmentUseCase(_adminRepository())
    fun getAllDeptUseCase()=ReadAllDepartmentUseCase(_adminRepository())
    fun readTeacherUseCase() = ReadTeacherUseCase(_adminRepository())
    fun insertTeacherUseCase() = InsertTeacherUseCase(_adminRepository())
    fun updateTeacherUseCase() = UpdateTeacherUseCase(_adminRepository())
    fun readFacultyUseCase() = ReadFacultyUseCase(_adminRepository())
    fun updateFacultyUseCase()= UpdateFacultyUseCase(_adminRepository())
    private fun _adminRepository() = DataFactory.createAdminRepository()

}