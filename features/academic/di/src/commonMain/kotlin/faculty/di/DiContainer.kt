package faculty.di

import data.DataFactory
import faculty.domain.usecase.RetrieveDepartmentsUseCase
import faculty.domain.usecase.RetrieveFactualityUseCase
import faculty.domain.usecase.RetrieveTeachersUseCase



object DiContainer {

    fun retrieveFacultyListUseCase( token: String?,):RetrieveFactualityUseCase= RetrieveFactualityUseCase(
        repository = DataFactory.createFacultyListRepository(token)
    )
    fun retrieveDepartListUseCase( token: String?,):RetrieveDepartmentsUseCase= RetrieveDepartmentsUseCase(
        repository = DataFactory.createFacultyListRepository(token)
    )

    fun retrieveTeacherListUseCase( token: String?,):RetrieveTeachersUseCase= RetrieveTeachersUseCase(
        repository = DataFactory.createFacultyListRepository(token )
    )

}