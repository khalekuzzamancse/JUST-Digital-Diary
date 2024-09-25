package database

import database.dao.TokenDao
import database.schema.TokenSchema
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertNull

class TokenDaoTest {

    private lateinit var db: Database
    private lateinit var tokenDao: TokenDao

    @Before
    fun createDb() {
        db = getDatabase()
        tokenDao = db.tokenDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testInsertAndRetrieveToken() = runBlocking {
        val token = TokenSchema(token = "sample_token")
        tokenDao.upsertToken(token)

        val retrievedToken = tokenDao.getToken()
        println("Inserted token: ${token.token}")
        println("Retrieved token: ${retrievedToken?.token}")

        assertEquals(token.token, retrievedToken?.token)
    }

    @Test
    fun testClearToken() = runBlocking {
        val token = TokenSchema(token = "sample_token")
        tokenDao.upsertToken(token)
        println("Inserted token: ${token.token}")

        tokenDao.clearToken()

        val retrievedToken = tokenDao.getToken()
        println("Token after clearing: ${retrievedToken?.token}")

        assertNull(retrievedToken)
    }

    @Test
    fun testUpdateToken() = runBlocking {
        val token = TokenSchema(token = "first_token")
        tokenDao.upsertToken(token)
        println("Inserted first token: ${token.token}")

        val updatedToken = TokenSchema(token = "updated_token")
        tokenDao.upsertToken(updatedToken)
        println("Updated token: ${updatedToken.token}")

        val retrievedToken = tokenDao.getToken()
        println("Retrieved updated token: ${retrievedToken?.token}")

        assertEquals("updated_token", retrievedToken?.token)
    }

    @Test
    fun testRetrieveEmptyDatabase() = runBlocking {
        val retrievedToken = tokenDao.getToken()
        println("Token from empty database: ${retrievedToken?.token}")

        assertNull(retrievedToken)
    }
}
