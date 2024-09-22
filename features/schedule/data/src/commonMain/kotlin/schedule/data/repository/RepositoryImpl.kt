package schedule.data.repository

import component.ApiServiceClient
import component.JsonParser
import schedule.data.dto.ClassScheduleMapper
import schedule.data.dto.ExamScheduleMapper
import schedule.data.schema.ClassScheduleSchema
import schedule.data.schema.ExamScheduleSchema
import schedule.domain.model.ClassScheduleModel
import schedule.domain.model.ExamScheduleModel
import schedule.domain.repository.Repository
import kotlinx.serialization.builtins.ListSerializer

/**
 * - It instance should be create using factory method
 * - Outside module should not create instance of it
 */
class RepositoryImpl internal constructor(
    private val apiServiceClient: ApiServiceClient,
    private val jsonParser: JsonParser
) : Repository {

    override fun retrieveClassSchedule(deptId: String): Result<List<ClassScheduleModel>> {
        val classLoader = this::class.java.classLoader
        val json =
            classLoader?.getResource("class_schedule_demo_data.json")
                ?.readText()//resource/teacher_list_demo_data.json
        if (json != null) {
            val result =
                json.let { jsonParser.parse(it, ListSerializer(ClassScheduleSchema.serializer())) }
            return result.fold(
                onSuccess = { entity ->
                    Result.success(entity.map {
                        ClassScheduleMapper().fromSchemaToModel(it)
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
                json.let { jsonParser.parse(it, ListSerializer(ExamScheduleSchema.serializer())) }
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


}

