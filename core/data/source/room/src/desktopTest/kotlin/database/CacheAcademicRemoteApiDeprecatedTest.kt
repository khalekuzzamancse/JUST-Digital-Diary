package database

import core.roomdb.db.DB
import domain.api.AcademicCacheApi
import core.roomdb.factory.RoomDBFactory
import core.roomdb.factory.getDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import kotlin.test.Test

class CacheAcademicRemoteApiDeprecatedTest {

    private lateinit var db: DB
    private lateinit var api: AcademicCacheApi

    @Before
    fun createDb() {
        db = getDatabase()
        api = RoomDBFactory().createAcademicCacheApi()
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
    fun read(): Unit = runBlocking {
       val response= api.readFacultiesOrThrow()
        println(response)
    }

}