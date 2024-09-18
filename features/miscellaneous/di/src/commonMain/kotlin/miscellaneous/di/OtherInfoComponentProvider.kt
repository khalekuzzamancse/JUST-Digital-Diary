package miscellaneous.di

import miscellaneous.data.repoisitory.OtherInfoRepositoryImpl


/**
 ** Instead of storing the resource we are returning it
 * so the client manually handle the lifecycle of the [LoginRepositoryImpl]
 */
object OtherInfoComponentProvider {
    fun getOtherInfoRepository(): OtherInfoRepositoryImpl {
        return OtherInfoRepositoryImpl()
    }


}