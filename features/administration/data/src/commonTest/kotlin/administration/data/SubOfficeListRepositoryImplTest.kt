package administration.data

import administration.data.sub_office.repoisitory.SubOfficeListRepositoryImpl
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

class SubOfficeListRepositoryImplTest {
    @Test
    fun `test for faculty list for null token`(){
        runBlocking {
            val token:String?=null
            val response= SubOfficeListRepositoryImpl().getSubOffices("01")
          //  println(response)
            assertTrue(response.isFailure)

        }

    }
    @Test
    fun `test for faculty list valid token`(){
        runBlocking {
            val token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJiZDI2ODQ3MC1kMmFhLTQxZmEtOSIsInJvbGVfaWQiOjEsImlhdCI6MTcxMTM0MDUzNCwiZXhwIjoxNzExMzgzNzM0fQ.YjVitfZPY-Lzt8zwD2W9RtYoELLQ0lvNyp0CVA_OdpE"
            val response= SubOfficeListRepositoryImpl().getSubOffices("01")
          //  println(response)
            assertTrue(response.isSuccess)

        }

    }


}