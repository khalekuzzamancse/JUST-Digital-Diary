package core.network

import core.network.post.post
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class NetworkManagerTest {
    @Test
    fun `check internet connection`(){
        runBlocking {
            val res=netManagerProvider().isInternetAvailable()
            println(res)
            val response=post<String>("","")
            println(response)
        }

    }
}