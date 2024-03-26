package administration.data

import administration.data.officers.repoisitory.AdminOfficerListRepositoryImpl
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

class EmployeeListRepositoryImplTest {
    @Test
    fun `test for teacher list for null token`(){
        runBlocking {
            val token:String?=null
            val response= AdminOfficerListRepositoryImpl().getOfficers("01")
          //  println(response)
            assertTrue(response.isFailure)

        }

    }
    @Test
    fun `test for faculty list valid token`(){
        runBlocking {
            val token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJiZDI2ODQ3MC1kMmFhLTQxZmEtOSIsInJvbGVfaWQiOjEsImlhdCI6MTcxMTM0MDUzNCwiZXhwIjoxNzExMzgzNzM0fQ.YjVitfZPY-Lzt8zwD2W9RtYoELLQ0lvNyp0CVA_OdpE"
            val response= AdminOfficerListRepositoryImpl().getOfficers("01")
            println(response)
            assertTrue(response.isSuccess)

        }

    }


}