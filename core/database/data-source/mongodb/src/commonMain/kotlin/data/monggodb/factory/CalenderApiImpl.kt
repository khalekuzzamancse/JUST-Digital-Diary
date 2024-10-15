@file:Suppress("unused")

package data.monggodb.factory

import data.monggodb.core.withExceptionHandle
import data.monggodb.db.CalenderCollection
import domain.api.CalenderApi

class CalenderApiImpl internal constructor() : CalenderApi {
    override suspend fun readOfCurrentYear() = withExceptionHandle {
        CalenderCollection().readOfCurrentYear()
    }

    override suspend fun insert(json: String) = withExceptionHandle {
       val res= CalenderCollection().insert(json)
        res
    }

    override suspend fun update(year: Int, json: String) = withExceptionHandle {
        CalenderCollection().updateOrThrow(year, json)
    }

    override suspend fun deleteCalender(year: Int): String {
        TODO("Not yet implemented")
    }

    override suspend fun readAll() = withExceptionHandle {
        CalenderCollection().readAll()
    }
}