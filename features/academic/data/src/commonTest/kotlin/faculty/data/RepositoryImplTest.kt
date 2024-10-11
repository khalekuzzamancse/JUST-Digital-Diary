package faculty.data

import data.DataFactory
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class RepositoryImplTest {

    @Test
    fun `test for faculty list valid token`() {
        runBlocking {
            val response = DataFactory.createPublicRepository().getFaculties("")
            response.onSuccess {
                println(response.getOrNull())
            }.onFailure {
                fail()
            }

        }

    }


}