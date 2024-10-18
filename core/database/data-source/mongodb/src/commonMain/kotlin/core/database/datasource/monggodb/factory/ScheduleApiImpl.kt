package core.database.datasource.monggodb.factory

import core.database.datasource.monggodb.core.withExceptionHandle
import core.database.datasource.monggodb.db.ScheduleCollection
import domain.api.ScheduleApi

class ScheduleApiImpl : ScheduleApi {
    private val collection = ScheduleCollection()

    override suspend fun insert(deptId: String, json: String)= withExceptionHandle {
        collection.insertOrThrow(deptId = deptId, json = json)
    }
    override suspend fun readByDept(deptId: String)= withExceptionHandle {
        collection.readByDeptOrThrow(deptId)
    }

    override suspend fun readAll()= withExceptionHandle {
        collection.readAllOrThrow()
    }
    override suspend fun update(id: String, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String): String {
        TODO("Not yet implemented")
    }

}