package faculty.data

import faculty.data.faculty.repoisitory.FacultyListRepositoryImpl
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

class FacultyListRepositoryImplTest {
    @Test
    fun `test for faculty list for null token`(){
        runBlocking {
            val token:String?=null
            val response= FacultyListRepositoryImpl().getFaculties()
          //  println(response)
            assertTrue(response.isFailure)

        }

    }
    @Test
    fun `test for faculty list valid token`(){
        runBlocking {
            val token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJiZDI2ODQ3MC1kMmFhLTQxZmEtOSIsInJvbGVfaWQiOjEsImlhdCI6MTcxMTI4ODk5NSwiZXhwIjoxNzExMzMyMTk1fQ.8Vazwh4x5lYFzXoPakm-5Qpnjz5UqIUbg2arlNQ-Ey4"
            val response= FacultyListRepositoryImpl().getFaculties()
            //println(response)
            assertTrue(response.isSuccess)

        }

    }


}