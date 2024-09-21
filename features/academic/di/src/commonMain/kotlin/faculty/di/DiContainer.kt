package faculty.di

import data.DataFactory
import faculty.domain.usecase.RetrieveDepartmentsUseCase
import faculty.domain.usecase.RetrieveFactualityUseCase
import faculty.domain.usecase.RetrieveTeachersUseCase



object DiContainer {

    fun retrieveFacultyListUseCase():RetrieveFactualityUseCase= RetrieveFactualityUseCase(
        repository = DataFactory.createFacultyListRepository()
    )
    fun retrieveDepartListUseCase():RetrieveDepartmentsUseCase= RetrieveDepartmentsUseCase(
        repository = DataFactory.createFacultyListRepository()
    )

    fun retrieveTeacherListUseCase():RetrieveTeachersUseCase= RetrieveTeachersUseCase(
        repository = DataFactory.createFacultyListRepository()
    )

}