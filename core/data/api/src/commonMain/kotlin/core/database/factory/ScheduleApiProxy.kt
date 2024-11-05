package core.database.factory

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
        TODO("Not yet implemented")
    }
}