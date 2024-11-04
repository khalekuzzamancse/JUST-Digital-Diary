package database

import core.roomdb.db.DB
import core.roomdb.factory.RoomDBFactory
import core.roomdb.factory.getDatabase
import domain.api.AcademicApiDeprecated
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import kotlin.test.Test

class RoomAcademicApiDeprecatedTest {

    private lateinit var db: DB
    private lateinit var api: AcademicApiDeprecated

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
       val response= api.readFaculties()
        println(response)
    }

}