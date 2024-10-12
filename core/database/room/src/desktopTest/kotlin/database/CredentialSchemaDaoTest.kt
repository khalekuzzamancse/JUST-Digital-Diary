package database

import core.database.db.Database
import core.database.getDatabase
import database.schema.CredentialSchema
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertNull

class CredentialSchemaDaoTest {

    private lateinit var db: Database
    private lateinit var credentialDao: core.database.dao.CredentialDao

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
    fun testInsertAndRetrieveCredential() = runBlocking {
        val credential = CredentialSchema(username = "user1", password = "pass1")
        credentialDao.upsertCredential(credential)

        val retrievedCredential = credentialDao.getCredential()
        println("Inserted credential: ${credential.username}, ${credential.password}")
        println("Retrieved credential: ${retrievedCredential?.username}, ${retrievedCredential?.password}")

        assertEquals(credential.username, retrievedCredential?.username)
        assertEquals(credential.password, retrievedCredential?.password)
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
