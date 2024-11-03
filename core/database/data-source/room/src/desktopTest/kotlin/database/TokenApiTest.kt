package database

import core.roomdb.apis.TokenApi
import core.roomdb.db.DB
import core.roomdb.factory.RoomDBFactory
import core.roomdb.factory.getDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class TokenApiTest {

    private lateinit var db: DB
    private lateinit var tokenApi: TokenApi

@Before
    fun createDb() {
        db = getDatabase()
        tokenApi = RoomDBFactory().createTokenApi()
    }

@After
    fun closeDb() {
        db.close()
    }

    @Test
    fun createDbTest() {
        db = getDatabase()
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
