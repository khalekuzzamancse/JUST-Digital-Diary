package com.just.cse.digitaldiary.twozerotwothree.core.di.features_other

import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.repoisitory.OtherInfoRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.data_layer.login.repository.LoginRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.data_layer.register.repoisitory.RegisterRepositoryImpl
/**
 ** Instead of storing the resource we are returning it
 * so the client manually handle the lifecycle of the [LoginRepositoryImpl]
 */
object OtherInfoComponentProvider {
    fun getOtherInfoRepository(): OtherInfoRepositoryImpl {
        return OtherInfoRepositoryImpl()
    }


}