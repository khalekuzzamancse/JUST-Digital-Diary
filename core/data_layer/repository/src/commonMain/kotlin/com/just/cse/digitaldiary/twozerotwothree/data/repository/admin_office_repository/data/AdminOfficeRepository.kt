package com.just.cse.digitaldiary.twozerotwothree.data.repository.admin_office_repository.data

import com.just.cse.digitaldiary.twozerotwothree.data.repository.admin_office_repository.model.AdminOffice
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AdminOfficeRepository {
   private val officeList= listOf(
        AdminOffice(
            name = "Offices",
            id = "1",
            subOfficeCnt = 2,
        ),
        AdminOffice(
            name = "Provost Offices",
            id = "2",
            subOfficeCnt = 4,
        ),
        AdminOffice(
            name = "Research Laboratory",
            id = "3",
            subOfficeCnt = 9,
        ),
        AdminOffice(
            name = "Institute and Cell",
            id = "4",
            subOfficeCnt = 1,
        ),
        AdminOffice(
            name = "Other",
            id = "4",
            subOfficeCnt = 6,
        )
    )
    private val _offices= MutableStateFlow(officeList)
    val offices=_offices.asStateFlow()
}