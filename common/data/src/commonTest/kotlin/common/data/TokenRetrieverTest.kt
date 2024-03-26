package common.data

import common.data.token.TokenRetriever
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

class TokenRetrieverTest {
    @Test
    fun `fetching token`(){
        runBlocking {
            val retriever=TokenRetriever()
            val result=retriever.tokenRequest("smsourav","test@123")
            println(result)
            assertTrue(result.isSuccess)
        }

    }
}