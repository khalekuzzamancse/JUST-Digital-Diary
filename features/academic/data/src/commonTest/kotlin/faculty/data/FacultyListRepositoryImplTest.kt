package faculty.data

import academic.data.faculty.repoisitory.FacultyListRepositoryImpl
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

class FacultyListRepositoryImplTest {

    @Test
    fun `test for faculty list valid token`(){
        runBlocking {
            val response= FacultyListRepositoryImpl().getFaculties()
            println(response)
            assertTrue(response.isSuccess)

        }

    }


}