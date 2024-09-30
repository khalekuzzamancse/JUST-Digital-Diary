package schedule.data.dto

import schedule.data.schema.ClassDetailSchema
import schedule.data.schema.ClassScheduleSchema
import schedule.data.schema.DailyClassScheduleSchema
import schedule.domain.model.ClassDetailModel
import schedule.domain.model.ClassScheduleModel
import schedule.domain.model.DailyClassScheduleModel

class ClassScheduleMapper {

    // Convert ClassScheduleSchema to ClassScheduleModel
    fun fromSchemaToModel(schema: ClassScheduleSchema): ClassScheduleModel {
        return ClassScheduleModel(
            dept = schema.dept,
            session = schema.session,
            year = schema.year,
            semester = schema.semester,
            routine = schema.routine.map { fromDailySchemaToModel(it) } // Map DailyClassScheduleSchema to DailyClassScheduleModel
        )
    }

    // Convert ClassScheduleModel to ClassScheduleSchema
    fun fromModelToSchema(model: ClassScheduleModel): ClassScheduleSchema {
        return ClassScheduleSchema(
            dept = model.dept,
            session = model.session,
            year = model.year,
            semester = model.semester,
            routine = model.routine.map { fromDailyModelToSchema(it) } // Map DailyClassScheduleModel to DailyClassScheduleSchema
        )
    }

    // Helper to convert DailyClassScheduleSchema to DailyClassScheduleModel
    private fun fromDailySchemaToModel(schema: DailyClassScheduleSchema): DailyClassScheduleModel {
        return DailyClassScheduleModel(
            day = schema.day,
            items = schema.items.map { fromDetailSchemaToModel(it) } // Map ClassDetailSchema to ClassDetailModel
        )
    }

    // Helper to convert DailyClassScheduleModel to DailyClassScheduleSchema
    private fun fromDailyModelToSchema(model: DailyClassScheduleModel): DailyClassScheduleSchema {
        return DailyClassScheduleSchema(
            day = model.day,
            items = model.items.map { fromDetailModelToSchema(it) } // Map ClassDetailModel to ClassDetailSchema
        )
    }

    // Helper to convert ClassDetailSchema to ClassDetailModel
    private fun fromDetailSchemaToModel(schema: ClassDetailSchema): ClassDetailModel {
        return ClassDetailModel(
            subject = schema.subject,
            time = schema.time,
            room = schema.room,
            durationInHours = schema.durationInHours
        )
    }

    // Helper to convert ClassDetailModel to ClassDetailSchema
    private fun fromDetailModelToSchema(model: ClassDetailModel): ClassDetailSchema {
        return ClassDetailSchema(
            subject = model.subject,
            time = model.time,
            room = model.room,
            durationInHours = model.durationInHours
        )
    }
}
