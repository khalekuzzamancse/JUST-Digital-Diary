@file:Suppress("FunctionName","unused")

package core.database.server
import core.network.ApiServiceClient
import core.network.Header
import core.network.JsonParser
import domain.api.AcademicApiDeprecated
import domain.factory.ContractFactory
import domain.factory.Serializers
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer

/**
 * This the api that belong to "https://backend.rnzgoldenventure.com"
 */
class ServerAcademicApiDeprecated internal constructor(
    private val parser: JsonParser,
    private val apiServiceClient: ApiServiceClient,
    private val token: String): AcademicApiDeprecated {
    private val feedbackService=ContractFactory.feedbackService()


    override suspend fun insertFaculty(json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun upsetFacultyList(json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun insertDept(facultyId: String, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun insertTeacher(deptId: String, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun readAllTeacher(): String {
        TODO("Not yet implemented")
    }

    /**Not handle any exception,propagate out the exception**/
    @Throws(Throwable::class)
    override suspend fun readFaculties(): String {
        val response = apiServiceClient.readJsonOrThrow(URL.ALL_FACULTY, Header(key = "Authorization", value =token ))
        //If not the faculty list response,there may be some error, propagate   to client for handling
        if (response._isNotFacultyListEntity()) return response

        //Response is faculty list it, use adapter to convert the format defined atb domain/contract layer
        return response
            ._toEntityList(FacultyReadEntity.serializer())
            ._adaptToDomainEntities()
            .toJson(Serializers.facultyReadEntity())
    }

    override suspend fun readFacultyById(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun readDeptById(id: String): String {
        TODO("Not yet implemented")
    }


    override suspend fun readDeptsUnderFaculty(facultyId: String): String {

        val response = apiServiceClient.readJsonOrThrow(
            url = "${URL.DEPT_UNDER_FACULTY}/$facultyId",
            header = Header(key = "Authorization", value = token)
        )
        if (response._isNotDeptListEntity()) return response

        return response
            ._toEntity(DepartmentListEntity.serializer()).departments
            ._toDomainDeptEntity(facultyId)
            .toJson(Serializers.deptReadEntity())
    }

    override suspend fun updateFaculty(facultyId: String, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun updateDept(deptId: String, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun updateTeacher(teacherId: String, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFaculty(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDepartment(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTeacher(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun readTeachersUnderDept(deptId: String): String {

        val response = apiServiceClient.readJsonOrThrow(
            url = "${URL.TEACHER_UNDER_DEPT}/$deptId",
            header = Header(key = "Authorization", value =token)
        )
        if (response._isNotTeacherListEntity()) return response

        return response
            ._toEntity(TeacherListEntity.serializer()).facultyMembers
            ._toDomainTeacherEntity(deptId)
            .toJson(Serializers.teacherReadEntity())
    }

    override suspend fun readTeacherById(teacherId: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun readAllDept(): String {
        TODO("Not yet implemented")
    }


    //TODO:Helper method section
    private fun String._isNotFacultyListEntity(): Boolean {
        return parser.parse(
            json = this,
            serializer = ListSerializer(FacultyReadEntity.serializer())
        ).isFailure
    }

    private fun <T> String._toEntityList(serializer: KSerializer<T>): List<T> {
        return this._toEntity(ListSerializer(serializer))
    }

    private fun <T> String._toEntity(serializer: KSerializer<T>) =
        parser.parseOrThrow(this, serializer)


    private fun List<FacultyReadEntity>._adaptToDomainEntities(): List<core.data.entity.academic.FacultyReadEntity> {
        return this.map { with(AcademicApiAdapter) { it.convert() } }
    }


    //Related to department
    private fun String._isNotDeptListEntity() =
        parser.parse(this, DepartmentListEntity.serializer()).isFailure
    private fun String._isNotTeacherListEntity() =
        parser.parse(this, TeacherListEntity.serializer()).isFailure

    private fun List<DepartmentEntity>._toDomainDeptEntity(facultyId: String) =
        this.map { with(AcademicApiAdapter) { it.convert(facultyId) } }

    private fun List<TeacherEntity>._toDomainTeacherEntity(deptId: String) =
        this.map { with(AcademicApiAdapter) { it.convert(deptId) } }

    private fun <T> List<T>.toJson(serializer: KSerializer<T>) =
        parser.toJsonListOrThrow(this, serializer)

}