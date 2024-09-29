package administration.di

import admin_office.domain.usecase.RetrieveOfficeListUseCase
import admin_office.domain.usecase.RetrieveOfficersUseCase
import admin_office.domain.usecase.RetrieveSubOfficeListUseCase
import administration.data.DataFactory
import administration.data.officers.repoisitory.AdminOfficerListRepositoryImpl
import administration.data.offices.repoisitory.AdminOfficeListRepositoryImpl
import administration.data.sub_office.repoisitory.SubOfficeListRepositoryImpl


/**
 ** Instead of storing the resource we are returning it
 * so the client manually handle the lifecycle of the [LoginRepositoryImpl]
 */
object DiContainer {
   private const val TOKEN="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJiZDI2ODQ3MC1kMmFhLTQxZmEtOSIsInJvbGVfaWQiOjEsImlhdCI6MTcxMTM0MDUzNCwiZXhwIjoxNzExMzgzNzM0fQ.YjVitfZPY-Lzt8zwD2W9RtYoELLQ0lvNyp0CVA_OdpE"

    fun getAdminOfficeRepository(): AdminOfficeListRepositoryImpl {

        return AdminOfficeListRepositoryImpl()
    }

    fun getAdminSubOfficeRepository(): SubOfficeListRepositoryImpl {
        return SubOfficeListRepositoryImpl()
    }

    fun getAdminOfficersListRepository(): AdminOfficerListRepositoryImpl {
        return AdminOfficerListRepositoryImpl()
    }
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