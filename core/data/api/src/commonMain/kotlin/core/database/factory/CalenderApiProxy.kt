package core.database.factory

import core.customexception.CustomException
import domain.api.CalenderApi

internal class CalenderApiProxy : CalenderApi {
    override suspend fun readOfCurrentYear(): String {
        //Avoid to because TODO throw Error that not sub type of Throwable
        throw CustomException.MessageFromServer("No holiday found")
    }

    override suspend fun insert(json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun update(year: Int, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCalender(year: Int): String {
        TODO("Not yet implemented")
    }

    override suspend fun readAll(): String {
        TODO("Not yet implemented")
    }
}