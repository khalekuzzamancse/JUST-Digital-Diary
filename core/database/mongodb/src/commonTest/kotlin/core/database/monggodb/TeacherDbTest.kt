package core.database.monggodb

import core.database.monggodb.database.DepartmentDb
import core.database.monggodb.database.TeacherDb
import core.database.monggodb.presentation.apis.DepartmentApiImpl
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class TeacherDbTest {

    @Test
    fun dataBaseTest() {
        runBlocking {
            try {
                val response = TeacherDb().read()
                println("Response;$response")

            }
            catch (e:Throwable){
                fail("Failed for :$e")

            }



        }

    }
    @Test
    fun apiTest() {
        runBlocking {
            try {
                val response = DepartmentApiImpl().read()
                println("Response;$response")

            }
            catch (e:Throwable){
                fail("Failed for :$e")

            }



        }

    }


}