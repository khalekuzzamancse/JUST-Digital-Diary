@file:Suppress("FunctionName")

package data.source

import core.database.api.ApiFactory
import core.network.ApiServiceClient
import core.network.Header
import core.network.JsonParser
import data.ModelMapper
import data.entity.public_.DepartmentListEntity
import data.entity.public_.FacultyEntity
import data.entity.public_.TeacherListEntity
import data.service.JsonHandler
import faculty.domain.model.public_.DepartmentModel
import faculty.domain.model.public_.FacultyModel
import faculty.domain.model.public_.TeacherModel
import kotlinx.serialization.builtins.ListSerializer

class RemoteDataSourceImpl internal constructor(
    private val apiServiceClient: ApiServiceClient,
    private val jsonParser: JsonParser,
    private val jsonHandler: JsonHandler
) : RemoteDataSource {
    override suspend fun getFaculties(token: String): Result<List<FacultyModel>> {
        val url = "https://backend.rnzgoldenventure.com/api/get/faculties"
//        println("Token is:$token")
//        val classLoader = this::class.java.classLoader
//        val json = classLoader?.getResource("faculty_list.json")?.readText()//resource/faculty_list.json

        try {
//            val json = apiServiceClient.retrieveJsonOrThrow(
//                url,
//                Header(key = "Authorization", value = token)
//            )
            val json = ApiFactory.academicAdminApi().readAllFaculty()
            /** Execution is here means server sent a response we have to parse it
             * - 3 possible cases: We got excepted  json or Json is a server message in format ServerResponseMessageEntity  or  Server send a json that format is not known yet,may be server change it json format or other
             */
            if (json._isFacultyListEntity()) {
                val entity =
                    jsonParser.parseOrThrow(json, ListSerializer(FacultyEntity.serializer()))
                return Result.success(ModelMapper.toFacultyModel(entity))
            } else
                return Result.failure(jsonHandler.parseAsServerMessageOrThrowCustomException(json))

        } catch (exception: Throwable) {
            return Result.failure(jsonHandler.createCustomException(exception))
        }


    }

    override suspend fun getDepartment(
        token: String,
        facultyId: String
    ): Result<List<DepartmentModel>> {
        val url = "https://backend.rnzgoldenventure.com/api/get/departments/$facultyId"
//        println("Token is:$token")
//        val classLoader = this::class.java.classLoader
//        val json = classLoader?.getResource("faculty_list.json")?.readText()//resource/faculty_list.json

        try {
            val json = apiServiceClient.retrieveJsonOrThrow(url, Header(key = "Authorization", value = token))
            if (json._isDeptListEntity()) {
                val entity = jsonParser.parseOrThrow(json, DepartmentListEntity.serializer())
                return Result.success(ModelMapper.toDeptModels(entity))
            } else
                return Result.failure(jsonHandler.parseAsServerMessageOrThrowCustomException(json))

        } catch (exception: Throwable) {
            return Result.failure(jsonHandler.createCustomException(exception))
        }
    }

    override suspend fun getTeachers(deptId: String, token: String): Result<List<TeacherModel>> {
        val url = "https://backend.rnzgoldenventure.com/api/get/faculty-members/$deptId"
        try {
            val json = apiServiceClient.retrieveJsonOrThrow(url, Header(key = "Authorization", value = token))
            if (json._isTeacherListEntity()) {
                println("TeacherListEntity")
                val entity = jsonParser.parseOrThrow(json, TeacherListEntity.serializer())
                return Result.success(ModelMapper.toTeacherModels(entity))
            } else
                return Result.failure(jsonHandler.parseAsServerMessageOrThrowCustomException(json))

        } catch (exception: Throwable) {
            return Result.failure(jsonHandler.createCustomException(exception))
        }
    }

    private fun String._isFacultyListEntity() =
        jsonParser.parse(this, ListSerializer(FacultyEntity.serializer())).isSuccess

    private fun String._isDeptListEntity() =
        jsonParser.parse(this, DepartmentListEntity.serializer()).isSuccess
    private fun String._isTeacherListEntity() =
        jsonParser.parse(this, TeacherListEntity.serializer()).isSuccess

}