package com.just.cse.digital_diary.features.admin_office.offices

import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemInfo2
import com.just.cse.digitaldiary.twozerotwothree.data.repository.admin_office_repository.model.AdminOffice
import com.just.cse.digitaldiary.twozerotwothree.data.repository.admin_office_repository.model.AdminOfficeSubOffice

fun getOfficesDestination(offices: List<AdminOffice>)=offices.map {
    NavigationItemInfo2(
        label = it.name,
        iconText = "${it.subOfficeCnt}",
        key = it.id
    )
}
fun getSubOfficeDestination(offices: List<AdminOfficeSubOffice>)=offices.map {
    NavigationItemInfo2(
        label = it.name,
        iconText = "${it.employeeCount}",
        key = it.id
    )
}

