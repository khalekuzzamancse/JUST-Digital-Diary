package com.just.cse.digitaldiary.twozerotwothree.data.repository.singed_in_user_info_repository.data

import com.just.cse.digitaldiary.twozerotwothree.data.repository.singed_in_user_info_repository.model.SignedInUser

object SignedInUserRepository {
    suspend fun getUserInfo(): SignedInUser {
        return SignedInUser(
            name = "Md Abdul Kalam",
            department = "Computer Science and Engineering"
        )
    }

}