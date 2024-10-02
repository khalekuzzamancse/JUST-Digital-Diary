package database

import core.database.db.Database
import core.database.factory.DatabaseFactory
import core.database.getDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class TokenApiTest {

    private lateinit var db: Database
    private lateinit var tokenApi: core.database.apis.TokenApi

    @Before
    fun createDb() {
        db = getDatabase()
        tokenApi = DatabaseFactory().createTokenApi()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testSaveAndRetrieveToken() = runBlocking {
        val token = "test-token"
        tokenApi.saveToken(token)

        val retrievedToken = tokenApi.getToken().firstOrNull()
        println("Inserted token: $token")
        println("Retrieved token: $retrievedToken")

        assertEquals(token, retrievedToken)
    }

    @Test
    fun testClearToken() = runBlocking {
        val token = "test-token"
        tokenApi.saveToken(token)
        println("Inserted token: $token")

        tokenApi.clearToken()

        val retrievedToken = tokenApi.getToken().firstOrNull()
        println("Token after clearing: $retrievedToken")

        assertNull(retrievedToken)
    }

    @Test
    fun testUpdateToken() = runBlocking {
        val token = "initial-token"
        tokenApi.saveToken(token)
        println("Inserted token: $token")

        val updatedToken = "updated-token"
        tokenApi.saveToken(updatedToken)
        println("Updated token: $updatedToken")

        val retrievedToken = tokenApi.getToken().firstOrNull()
        println("Retrieved updated token: $retrievedToken")

        assertEquals(updatedToken, retrievedToken)
    }

    @Test
    fun testRetrieveEmptyDatabase() = runBlocking {
        val retrievedToken = tokenApi.getToken().firstOrNull()
        println("Token from empty database: $retrievedToken")

        assertNull(retrievedToken)
    }
}
