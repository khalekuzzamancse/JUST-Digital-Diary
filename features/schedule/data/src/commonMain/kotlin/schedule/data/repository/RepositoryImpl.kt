@file:Suppress("functionName")
package schedule.data.repository

import core.database.api.ApiFactory
import core.network.JsonParser
import schedule.data.service.JsonHandler
import data.service.withExceptionHandle
import schedule.data.mapper.ClassScheduleMapper
import schedule.data.mapper.ExamScheduleMapper
import schedule.data.entity.ClassScheduleWriteEntity
import schedule.data.entity.ExamScheduleSchema
import schedule.domain.model.ClassScheduleModel
import schedule.domain.model.ExamScheduleModel
import schedule.domain.repository.Repository
import kotlinx.serialization.builtins.ListSerializer
import schedule.data.entity.DepartmentEntity
import schedule.data.mapper.ModelMapper
import schedule.domain.model.DepartmentModel

/**
 * - It instance should be create using factory method
 * - Outside module should not create instance of it
 */
class RepositoryImpl internal constructor(
    private val handler: JsonHandler,
    private val parser: JsonParser
) : Repository {
    override suspend fun insert(model: ClassScheduleModel, deptId: String) :Result<Unit>{
        return with(handler) {
            withExceptionHandle {
                val entity =ClassScheduleMapper().modelToEntity(model)
                val dataJson = parser.toJsonOrThrow(entity,ClassScheduleWriteEntity.serializer())
                println(dataJson)
                val responseJson="""
                    {
                     message:"Not implemented yet"
                    }
                """.trimIndent()
//                val responseJson = ApiFactory.academicApi().updateDept(
//                    deptId = deptId,
//                    json = dataJson
//                )
                return Result.failure(responseJson.parseAsServerMessageOrThrow())
            }
        }

    }

    override suspend fun readAllDept(): Result<List<DepartmentModel>> {
        return with(handler) {
            withExceptionHandle {
                val json = ApiFactory.academicApi().readAllDept()

                if (json._isDepartmentListEntity()) {
                    val entities =
                        json.parseOrThrow(ListSerializer(DepartmentEntity.serializer()))
                    return Result.success(
                        with(ModelMapper) {
                            entities.sortedBy { it.priority }.map { it.toModel() }
                        }
                    )
                } else
                    return Result.failure(json.parseAsServerMessageOrThrow())

            }
        }

    }

    override fun retrieveClassSchedule(deptId: String): Result<List<ClassScheduleModel>> {
        val classLoader = this::class.java.classLoader
        val json =
            classLoader?.getResource("class_schedule_demo_data.json")
                ?.readText()//resource/teacher_list_demo_data.json
        if (json != null) {
            val result =
                json.let { parser.parse(it, ListSerializer(ClassScheduleWriteEntity.serializer())) }
            return result.fold(
                onSuccess = { entity ->
                    Result.success(entity.map {
                        ClassScheduleMapper().entityToModel(it)
                    })
                },
                onFailure = { exception ->
                    Result.failure(exception)
                }
            )
        }
        return Result.failure(Throwable("Json is full,failed to read the file"))

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

}

