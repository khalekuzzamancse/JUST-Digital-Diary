@file:Suppress("functionName")

package schedule.data.repository

import core.database.factory.ApiFactory
import core.network.JsonParser
import schedule.data.service.withExceptionHandle
import kotlinx.serialization.builtins.ListSerializer
import schedule.data.entity.DepartmentEntity
import schedule.data.entity.ExamScheduleSchema
import schedule.data.entity.ClassScheduleReadEntity
import schedule.data.entity.ClassScheduleWriteEntity
import schedule.data.mapper.ExamScheduleMapper
import schedule.data.mapper.EntityModelMapper
import schedule.data.mapper.EntityModelMapper.toEntity
import schedule.data.service.JsonHandler
import schedule.domain.model.ClassScheduleReadModel
import schedule.domain.model.ClassScheduleWriteModel
import schedule.domain.model.DepartmentModel
import schedule.domain.model.ExamScheduleModel
import schedule.domain.repository.Repository

/**
 * - It instance should be create using factory method
 * - Outside module should not create instance of it
 */
class RepositoryImpl internal constructor(
    private val handler: JsonHandler,
    private val parser: JsonParser,
    private val token:String?=null,
) : Repository {
    private val api= ApiFactory.scheduleApi()
    override suspend fun insert(model: ClassScheduleWriteModel, deptId: String): Result<Unit> {
        return with(handler) {
            withExceptionHandle {
                val entity = model.toEntity()
                val dataJson = parser.toJsonOrThrow(entity, ClassScheduleWriteEntity.serializer())
                val responseJson = api.insert(deptId = deptId, json = dataJson)
                return Result.failure(responseJson.parseAsServerMessageOrThrow())
            }
        }

    }

    override suspend fun readAllDept(): Result<List<DepartmentModel>> {
        return with(handler) {
            withExceptionHandle {
                val json = ApiFactory.academicApi(token).readAllDept()

                if (json._isDepartmentListEntity()) {
                    val entities =
                        json.parseOrThrow(ListSerializer(DepartmentEntity.serializer()))
                    return Result.success(
                        with(EntityModelMapper) {
                            entities.sortedBy { it.priority }.map { it.toModel() }
                        }
                    )
                } else
                    return Result.failure(json.parseAsServerMessageOrThrow())

            }
        }

    }

    override suspend fun retrieveClassSchedule(): Result<List<ClassScheduleReadModel>> {
        return with(handler) {
            withExceptionHandle {
                val json = ApiFactory.scheduleApi().readAll()

                if (json._isClassScheduleListEntity()) {
                    val entities =
                        json.parseOrThrow(ListSerializer(ClassScheduleReadEntity.serializer()))
                    return Result.success(
                        with(EntityModelMapper) {
                            entities.map { it.toModel() }
                        }
                    )
                } else
                    return Result.failure(json.parseAsServerMessageOrThrow())

            }


        }
    }

    override fun retrieveExamSchedule(deptId: String): Result<List<ExamScheduleModel>> {
        val classLoader = this::class.java.classLoader
        val json =
            classLoader?.getResource("exam_schedule_demo_data.json")
                ?.readText()//resource/teacher_list_demo_data.json
        if (json != null) {
            val result =
                json.let { parser.parse(it, ListSerializer(ExamScheduleSchema.serializer())) }
            return result.fold(
                onSuccess = { entity ->
                    Result.success(
                        entity.map { ExamScheduleMapper().fromSchemaToModel(it) })
                },
                onFailure = { exception ->
                    Result.failure(exception)
                }
            )
        }
        return Result.failure(Throwable("Json is full,failed to read the file"))
    }

    private fun String._isDepartmentListEntity() =
        parser.parse(this, ListSerializer(DepartmentEntity.serializer())).isSuccess

    private fun String._isClassScheduleListEntity() =
        parser.parse(this, ListSerializer(ClassScheduleReadEntity.serializer())).isSuccess

}

