package administration.di

import administration.data.officers.repoisitory.AdminOfficerListRepositoryImpl
import administration.data.offices.repoisitory.AdminOfficeListRepositoryImpl
import administration.data.sub_office.repoisitory.AdminSubOfficeListRepositoryImpl


/**
 ** Instead of storing the resource we are returning it
 * so the client manually handle the lifecycle of the [LoginRepositoryImpl]
 */
object AdminOfficeComponentProvider {
   private const val TOKEN="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJiZDI2ODQ3MC1kMmFhLTQxZmEtOSIsInJvbGVfaWQiOjEsImlhdCI6MTcxMTI4ODk5NSwiZXhwIjoxNzExMzMyMTk1fQ.8Vazwh4x5lYFzXoPakm-5Qpnjz5UqIUbg2arlNQ-Ey4"


    fun getAdminOfficeRepository(): AdminOfficeListRepositoryImpl {

        return AdminOfficeListRepositoryImpl(TOKEN)
    }

    fun getAdminSubOfficeRepository(): AdminSubOfficeListRepositoryImpl {
        return AdminSubOfficeListRepositoryImpl(TOKEN)
    }

    fun getAdminOfficersListRepository(): AdminOfficerListRepositoryImpl {
        return AdminOfficerListRepositoryImpl(TOKEN)
    }
}