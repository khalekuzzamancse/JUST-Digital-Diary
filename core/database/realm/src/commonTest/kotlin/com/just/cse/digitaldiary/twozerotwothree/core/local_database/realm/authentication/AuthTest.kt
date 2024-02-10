package com.just.cse.digitaldiary.twozerotwothree.core.local_database.realm.authentication

import com.just.cse.digitaldiary.twozerotwothree.core.local_database.realm.RealmAuthentication
import com.just.cse.digitaldiary.twozerotwothree.core.local_database.realm.authentication.responose_model.SignedInUserResponseModel
import kotlinx.coroutines.runBlocking
import kotlin.test.Test//instead of JUnit.test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class AuthTest {
    private val database = RealmAuthentication

    companion object {
        private const val TAG = "AuthTest:: "
        private fun log(message: String) {
            println("$TAG$message")
        }
    }

    @Test
    fun saveSignInInfoTest() {
        runBlocking {
            val requestModel = SignedInUserResponseModel(
                username = "test",
                password = "test@123"
            )
            val response = database.saveSignInInfo(requestModel)
            assertEquals(requestModel, response)
        }

    }

    @Test
    fun getSingedInUserInfoTest() {
        runBlocking {
            val response = database.getSingedInUserInfo()
            log("getSingedInUserInfoTest():: $response")
        }
    }

    @Test
    fun signOutTest() {
        runBlocking {
            database.signOut()
            val response = database.signOut()
            assertEquals(true, response)
        }
    }
    @Test
    fun isSignedInTest(){
        val response = database.isSignedIn()
      log(if (response)"Signed in" else "Not signed in")
    }
    @Test
    fun updateTokenTest() {
        val response: Boolean = database.updateToken("new token")
        log("updatedTokenTest:result=$response")
        assertEquals(true, response)
    }
    @Test
    fun getTokenTest() {
        log("getAuthTokenTest:${database.getToken()}")
    }
}