package com.just.cse.digitaldiary.twozerotwothree.data.repository.admin_office_repository.data

import com.just.cse.digitaldiary.twozerotwothree.core.network.admin_office_list.data.SubOfficeListFetcher
import com.just.cse.digitaldiary.twozerotwothree.data.repository.admin_office_repository.model.AdminOffice
import com.just.cse.digitaldiary.twozerotwothree.data.repository.admin_office_repository.model.AdminOfficeSubOffice
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AdminOfficeSubOfficeRepository {
    private val officeList = listOf(
        AdminOfficeSubOffice(
            name = "Office of the Vice Chancellor",
            id = "1",
            employeeCount = 10,
        ),
        AdminOfficeSubOffice(
            name = "Office of the  Treasurer",
            id = "2",
            employeeCount = 26,
        ),
        AdminOfficeSubOffice(
            name = "Office of the  Registrar",
            id = "3",
            employeeCount = 33,
        ),
        AdminOfficeSubOffice(
            name = "Office of the  Librarian",
            id = "4",
            employeeCount = 20,
        ),
        AdminOfficeSubOffice(
            name = "Office of the Proctor",
            id = "4",
            employeeCount = 25,
        )
    )

    suspend fun getSubOffice(officeId: String):List<AdminOfficeSubOffice>{
       return SubOfficeListFetcher().fetch(officeId).map {
            AdminOfficeSubOffice(
                name = it.name,
                id=it.sub_office_id,
                employeeCount = it.members_count
            )
        }
    }
}