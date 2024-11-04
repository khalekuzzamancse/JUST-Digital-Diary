package core.database.api

import core.database.factory.ApiFactory
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class ServerAcademicApiImplFacadeTest {
    private val api= ApiFactory.serverApi("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJmMzFlNGVmYS1lYTBhLTRmYTItOSIsInJvbGVfaWQiOjEsInVzZXJuYW1lIjoia2hhbGVrMTkwMTQyIiwiaWF0IjoxNzMwNjkxMzYxLCJleHAiOjE3MzEyOTYxNjF9.SYSeFsdEmDPzhsS4IsbdMjOZqZxdsWEwfZGdwmX3ARk")
    @Test
    fun read(): Unit = runBlocking {
        val response=api.readFaculties()
        println(response)
    }

}
