package core.database.server

import core.customexception.CustomException
import core.data.entity.academic.DepartmentReadEntity
import core.data.entity.academic.DepartmentWriteEntity
import core.data.entity.academic.FacultyReadEntity
import core.data.entity.academic.FacultyWriteEntity
import core.data.entity.academic.TeacherReadEntity
import core.data.entity.academic.TeacherWriteEntity
import core.network.ApiServiceClient
import core.network.Header
import core.network.JsonParser
import domain.api.AcademicApi
import domain.service.FeedbackMessageService
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer

class ServerAcademicApi2(
    private val parser: JsonParser,
    private val apiServiceClient: ApiServiceClient,
    private val token: String,
    private val feedbackService:FeedbackMessageService):AcademicApi {

    override suspend fun readFacultiesOrThrow(): List<FacultyReadEntity> {
        val response = apiServiceClient.readJsonOrThrow(URL.ALL_FACULTY, Header(key = "Authorization", value =token ))
        //If not the faculty list response,there may be some error, propagate   to client for handling
        if (response._isNotFacultyListEntity())
            throw  CustomException.ServerFeedbackExecpton(
                message = feedbackService.asFeedbackEntityOrThrow(response).message
            )

        //Response is faculty list it, use adapter to convert the format defined atb domain/contract layer
        return response
            ._toEntityList(core.database.server.FacultyReadEntity.serializer())
            ._adaptToDomainEntities()

    }

    override suspend fun insertFacultyOrThrow(entity: FacultyWriteEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun insertFacultiesOrThrow(entities: List<FacultyWriteEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun readFacultyByIdOrThrow(id: String): FacultyReadEntity {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFacultyOrThrow(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun insertDeptOrThrow(facultyId: String, entity: DepartmentWriteEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun insertDeptsOrThrow(
        facultyId: String,
        entities: List<DepartmentWriteEntity>
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun readAllDeptOrThrow(): List<DepartmentReadEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun readDeptsOrThrow(facultyId: String): List<DepartmentReadEntity> {
        val response = apiServiceClient.readJsonOrThrow(
            url = "${URL.DEPT_UNDER_FACULTY}/$facultyId",
            header = Header(key = "Authorization", value = token)
        )
        if (response._isNotDeptListEntity())
            throw  CustomException.ServerFeedbackExecpton(
                message = feedbackService.asFeedbackEntityOrThrow(response).message
            )

        return response
            ._toEntity(DepartmentListEntity.serializer()).departments
            ._toDomainDeptEntity(facultyId)

    }

    override suspend fun readDeptOrThrow(id: String): DepartmentReadEntity {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDepartmentOrThrow(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun insertTeacherOrThrow(deptId: String, entity: TeacherWriteEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun insertTeachersOrThrow(deptId: String, entities: List<TeacherWriteEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun readAllTeacherOrThrow(): List<TeacherReadEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun readTeachersOrThrow(deptId: String): List<TeacherReadEntity> {
        val response = apiServiceClient.readJsonOrThrow(
            url = "${URL.TEACHER_UNDER_DEPT}/$deptId",
            header = Header(key = "Authorization", value =token)
        )
        if (response._isNotTeacherListEntity())
            throw  CustomException.ServerFeedbackExecpton(
                message = feedbackService.asFeedbackEntityOrThrow(response).message
            )

        return response
            ._toEntity(TeacherListEntity.serializer()).facultyMembers
            ._toDomainTeacherEntity(deptId)

    }

    override suspend fun readTeacherOrThrow(teacherId: String): TeacherReadEntity {
        TODO("Not yet implemented")
    }

    override suspend fun updateFacultyOrThrow(facultyId: String, entity: FacultyWriteEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun updateDeptOrThrow(deptId: String, entity: DepartmentWriteEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun updateTeacherOrThrow(teacherId: String, entity: TeacherWriteEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTeacherOrThrow(id: String) {
        TODO("Not yet implemented")
    }
    //TODO:Helper method section
    private fun String._isNotFacultyListEntity(): Boolean {
        return parser.parse(
            json = this,
            serializer = ListSerializer(core.database.server.FacultyReadEntity.serializer())
        ).isFailure
    }

    private fun <T> String._toEntityList(serializer: KSerializer<T>): List<T> {
        return this._toEntity(ListSerializer(serializer))
    }

    private fun <T> String._toEntity(serializer: KSerializer<T>) =
        parser.parseOrThrow(this, serializer)


    private fun List<core.database.server.FacultyReadEntity>._adaptToDomainEntities(): List<core.data.entity.academic.FacultyReadEntity> {
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

}