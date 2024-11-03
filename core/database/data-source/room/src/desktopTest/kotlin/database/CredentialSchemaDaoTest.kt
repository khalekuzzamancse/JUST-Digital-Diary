package database

import core.roomdb.dao.CredentialDao
import core.roomdb.db.DB
import core.roomdb.schema.CredentialSchema
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import core.roomdb.factory.getDatabase
import kotlin.test.Test
import kotlin.test.assertNull

class CredentialSchemaDaoTest {

    private lateinit var db: DB
    private lateinit var credentialDao: CredentialDao

    @Before
    fun createDb() {
        db = getDatabase()
        credentialDao = db.credentialDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insert() = runBlocking {
            val credential = CredentialSchema(username = "user1", password = "pass1")
            credentialDao.upsertCredential(credential)

        val response = credentialDao.getCredential()
        assertEquals(credential.username,response?.username)
        assertEquals(credential.password,response?.password)

    }
    @Test
    fun read() = runBlocking {
        val retrievedCredential = credentialDao.getCredential()
        println("Retrieved credential: ${retrievedCredential?.username}, ${retrievedCredential?.password}")
    }

    @Test
    fun testClearCredential() = runBlocking {
        val credential = CredentialSchema(username = "user1", password = "pass1")
        credentialDao.upsertCredential(credential)
        println("Inserted credential: ${credential.username}, ${credential.password}")

        credentialDao.clearCredential()

        val retrievedCredential = credentialDao.getCredential()
        println("Credential after clearing: ${retrievedCredential?.username}, ${retrievedCredential?.password}")

        assertNull(retrievedCredential)
    }

    @Test
    fun testUpdateCredential() = runBlocking {
        val credential = CredentialSchema(username = "user1", password = "pass1")
        credentialDao.upsertCredential(credential)
        println("Inserted first credential: ${credential.username}, ${credential.password}")

        val updatedCredential = CredentialSchema(username = "updatedUser", password = "updatedPass")
        credentialDao.upsertCredential(updatedCredential)
        println("Updated credential: ${updatedCredential.username}, ${updatedCredential.password}")

        val retrievedCredential = credentialDao.getCredential()
        println("Retrieved updated credential: ${retrievedCredential?.username}, ${retrievedCredential?.password}")

        assertEquals("updatedUser", retrievedCredential?.username)
        assertEquals("updatedPass", retrievedCredential?.password)
    }

    @Test
    fun testRetrieveEmptyDatabase() = runBlocking {
        val retrievedCredential = credentialDao.getCredential()
        println("Credential from empty database: ${retrievedCredential?.username}, ${retrievedCredential?.password}")

        assertNull(retrievedCredential)
    }
}
