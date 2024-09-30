package administration.di

import admin_office.domain.usecase.RetrieveOfficeListUseCase
import admin_office.domain.usecase.RetrieveOfficersUseCase
import admin_office.domain.usecase.RetrieveSubOfficeListUseCase
import administration.data.factory.DataFactory


object DiContainer {

    fun createGetOfficesUseCase(token:String?)=RetrieveOfficeListUseCase(
        repository = DataFactory.createRepository(token)
    )
    fun createGetSubOfficesUseCase(token:String?)=RetrieveSubOfficeListUseCase(
        repository = DataFactory.createRepository(token)
    )
    fun createGetAdminOfficersUseCase(token:String?)= RetrieveOfficersUseCase(
        repository = DataFactory.createRepository(token)
    )
}