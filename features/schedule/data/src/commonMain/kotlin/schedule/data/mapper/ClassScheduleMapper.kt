package schedule.data.mapper

import schedule.data.entity.ClassDetailEntity
import schedule.data.entity.ClassScheduleWriteEntity
import schedule.data.entity.ClassEntity
import schedule.domain.model.ClassDetailModel
import schedule.domain.model.ClassScheduleModel
import schedule.domain.model.ClassModel

class ClassScheduleMapper {

    // Convert ClassScheduleSchema to ClassScheduleModel
    fun entityToModel(schema: ClassScheduleWriteEntity): ClassScheduleModel {
        return ClassScheduleModel(
            session = schema.session,
            year = schema.year,
            semester = schema.semester,
            routine = schema.routine.map { fromDailySchemaToModel(it) } // Map DailyClassScheduleSchema to DailyClassScheduleModel
        )
    }

    // Convert ClassScheduleModel to ClassScheduleSchema
    fun modelToEntity(model: ClassScheduleModel): ClassScheduleWriteEntity {
        return ClassScheduleWriteEntity(
            session = model.session,
            year = model.year,
            semester = model.semester,
            routine = model.routine.map { fromDailyModelToSchema(it) } // Map DailyClassScheduleModel to DailyClassScheduleSchema
        )
    }

    // Helper to convert DailyClassScheduleSchema to DailyClassScheduleModel
    private fun fromDailySchemaToModel(schema: ClassEntity): ClassModel {
        return ClassModel(
            day = schema.day,
            classes = schema.items.map { fromDetailSchemaToModel(it) } // Map ClassDetailSchema to ClassDetailModel
        )
    }

    // Helper to convert DailyClassScheduleModel to DailyClassScheduleSchema
    private fun fromDailyModelToSchema(model: ClassModel): ClassEntity {
        return ClassEntity(
            day = model.day,
            items = model.classes.map { fromDetailModelToSchema(it) } // Map ClassDetailModel to ClassDetailSchema
        )
    }

    // Helper to convert ClassDetailSchema to ClassDetailModel
    private fun fromDetailSchemaToModel(schema: ClassDetailEntity): ClassDetailModel {
        return ClassDetailModel(
            subject = schema.subject,
            time = schema.time,
            teacherName = schema.room,
            durationInHours = schema.durationInHours
        )
    }

    // Helper to convert ClassDetailModel to ClassDetailSchema
    private fun fromDetailModelToSchema(model: ClassDetailModel): ClassDetailEntity {
        return ClassDetailEntity(
            subject = model.subject,
            time = model.time,
            room = model.teacherName,
            durationInHours = model.durationInHours
        )
    }
}
