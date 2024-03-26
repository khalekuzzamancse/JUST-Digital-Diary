package administration.di

import administration.data.officers.repoisitory.AdminOfficerListRepositoryImpl
import administration.data.offices.repoisitory.AdminOfficeListRepositoryImpl
import administration.data.sub_office.repoisitory.AdminSubOfficeListRepositoryImpl


/**
 ** Instead of storing the resource we are returning it
 * so the client manually handle the lifecycle of the [LoginRepositoryImpl]
 */
object AdminOfficeComponentProvider {
   private const val TOKEN="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJiZDI2ODQ3MC1kMmFhLTQxZmEtOSIsInJvbGVfaWQiOjEsImlhdCI6MTcxMTM0MDUzNCwiZXhwIjoxNzExMzgzNzM0fQ.YjVitfZPY-Lzt8zwD2W9RtYoELLQ0lvNyp0CVA_OdpE"

    fun getAdminOfficeRepository(): AdminOfficeListRepositoryImpl {

        return AdminOfficeListRepositoryImpl()
    }

    fun getAdminSubOfficeRepository(): AdminSubOfficeListRepositoryImpl {
        return AdminSubOfficeListRepositoryImpl()
    }

    fun getAdminOfficersListRepository(): AdminOfficerListRepositoryImpl {
        return AdminOfficerListRepositoryImpl()
    }
}