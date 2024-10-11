package faculty.di

import data.DataFactory
import faculty.domain.usecase.public_.RetrieveDepartmentsUseCase
import faculty.domain.usecase.public_.RetrieveFactualityUseCase
import faculty.domain.usecase.public_.RetrieveTeachersUseCase



object DiContainer {

    fun retrieveFacultyListUseCase( token: String?,): RetrieveFactualityUseCase = RetrieveFactualityUseCase(
        repository = DataFactory.createPublicRepository(token)
    )
    fun retrieveDepartListUseCase( token: String?,): RetrieveDepartmentsUseCase = RetrieveDepartmentsUseCase(
        repository = DataFactory.createPublicRepository(token)
    )

    fun retrieveTeacherListUseCase( token: String?,): RetrieveTeachersUseCase = RetrieveTeachersUseCase(
        repository = DataFactory.createPublicRepository(token )
    )

}