package faculty.di

import data.DataFactory
import faculty.domain.usecase.public_.RetrieveDepartmentsUseCase
import faculty.domain.usecase.public_.RetrieveFactualityUseCase
import faculty.domain.usecase.public_.RetrieveTeachersUseCase



object DiContainer {

    fun retrieveFacultyListUseCase( token: String?,): RetrieveFactualityUseCase = RetrieveFactualityUseCase(
        repository = DataFactory.createFacultyListRepository(token)
    )
    fun retrieveDepartListUseCase( token: String?,): RetrieveDepartmentsUseCase = RetrieveDepartmentsUseCase(
        repository = DataFactory.createFacultyListRepository(token)
    )

    fun retrieveTeacherListUseCase( token: String?,): RetrieveTeachersUseCase = RetrieveTeachersUseCase(
        repository = DataFactory.createFacultyListRepository(token )
    )

}