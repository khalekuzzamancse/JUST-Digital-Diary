package database

import core.roomdb.db.DB
import core.roomdb.factory.RoomDBFactory
import core.roomdb.factory.getDatabase
import domain.api.AcademicApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import kotlin.test.Test

class AcademicApiTest {

    private lateinit var db: DB
    private lateinit var api: AcademicApi

    @Before
    fun createDb() {
        db = getDatabase()
        api = RoomDBFactory().createAcademicApi2()
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
       val response= api.readAllFaculty()
        println(response)
    }

}