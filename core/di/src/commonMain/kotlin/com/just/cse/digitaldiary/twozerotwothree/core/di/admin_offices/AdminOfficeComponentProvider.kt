package com.just.cse.digitaldiary.twozerotwothree.core.di.admin_offices

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_sub_offices.repoisitory.AdminSubOfficeListRepository
import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.repoisitory.AdminOfficerListRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_offices.repoisitory.AdminOfficeListRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_sub_offices.repoisitory.AdminSubOfficeListRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.data_layer.login.repository.LoginRepositoryImpl
import com.just.cse.digitaldiary.twozerotwothree.core.di.auth.AuthComponentProvider

/**
 ** Instead of storing the resource we are returning it
 * so the client manually handle the lifecycle of the [LoginRepositoryImpl]
 */
object AdminOfficeComponentProvider {
    private val token = AuthComponentProvider.authToken

    fun getAdminOfficeRepository(): AdminOfficeListRepositoryImpl {

        return AdminOfficeListRepositoryImpl(token)
    }

    fun getAdminSubOfficeRepository(): AdminSubOfficeListRepositoryImpl {
        return AdminSubOfficeListRepositoryImpl(token)
    }

    fun getAdminOfficersListRepository(): AdminOfficerListRepositoryImpl {
        return AdminOfficerListRepositoryImpl(token)
    }
}