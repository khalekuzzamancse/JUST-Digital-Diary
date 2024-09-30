package core.network

import _old.network.netManagerProvider
import _old.network.post.post
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class NetworkManagerTest {
    @Test
    fun `check internet connection`(){
        runBlocking {
            val res= netManagerProvider().isInternetAvailable()
            println(res)
            val response= post<String>("","")
            println(response)
        }

    }
}