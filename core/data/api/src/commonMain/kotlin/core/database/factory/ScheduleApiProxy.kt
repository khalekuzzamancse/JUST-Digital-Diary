package core.database.factory

import core.customexception.CustomException
import domain.api.ScheduleApi

internal class ScheduleApiProxy: ScheduleApi {
    override suspend fun readByDept(deptId: String): String {
        TODO("Not yet implemented")

    }

    override suspend fun insert(deptId: String, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun update(id: String, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun readAll(): String {
        //Avoid to because TODO throw Error that not sub type of Throwable
        throw  CustomException.ServerFeedbackExecpton(message = "No Schedule found")
    }
}