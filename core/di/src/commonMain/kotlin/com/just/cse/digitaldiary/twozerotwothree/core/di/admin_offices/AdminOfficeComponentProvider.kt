package com.just.cse.digitaldiary.twozerotwothree.core.di.admin_offices

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_sub_offices.repoisitory.AdminSubOfficeListRepository
import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.repoisitory.AdminOfficerListRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_offices.repoisitory.AdminOfficeListRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_sub_offices.repoisitory.AdminSubOfficeListRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.data_layer.login.repository.LoginRepositoryImpl

/**
 ** Instead of storing the resource we are returning it
 * so the client manually handle the lifecycle of the [LoginRepositoryImpl]
 */
object AdminOfficeComponentProvider {
    private const val token ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiIxOTExMDEuY3NlOGFlNDdkYTdkY2VkIiwicm9sZV9pZCI6MTMsImlhdCI6MTcwNzMxNjgyNywiZXhwIjoxNzA3NDg5NjI3fQ.bHE4wQAIX7oa5uYNQOMgbvr4KMPIU3h8NixfMtsyxKg"

    fun getAdminOfficeRepository(): AdminOfficeListRepositoryImpl {
        return AdminOfficeListRepositoryImpl(token)
    }

    fun getAdminSubOfficeRepository(): AdminSubOfficeListRepositoryImpl {
        return AdminSubOfficeListRepositoryImpl(token)
    }
    fun getAdminOfficersListRepository():AdminOfficerListRepositoryImpl {
        return AdminOfficerListRepositoryImpl(token)
    }
}