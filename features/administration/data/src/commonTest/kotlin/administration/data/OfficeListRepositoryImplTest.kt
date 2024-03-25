package administration.data

import administration.data.offices.repoisitory.AdminOfficeListRepositoryImpl
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

class OfficeListRepositoryImplTest {
    @Test
    fun `test for faculty list for null token`(){
        runBlocking {
            val token:String?=null
            val response= AdminOfficeListRepositoryImpl(token).getAdminOffices()
          //  println(response)
            assertTrue(response.isFailure)

        }

    }
    @Test
    fun `test for faculty list valid token`(){
        runBlocking {
            val token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJiZDI2ODQ3MC1kMmFhLTQxZmEtOSIsInJvbGVfaWQiOjEsImlhdCI6MTcxMTM0MDUzNCwiZXhwIjoxNzExMzgzNzM0fQ.YjVitfZPY-Lzt8zwD2W9RtYoELLQ0lvNyp0CVA_OdpE"
            val response= AdminOfficeListRepositoryImpl(token).getAdminOffices()
            //println(response)
            assertTrue(response.isSuccess)

        }

    }


}