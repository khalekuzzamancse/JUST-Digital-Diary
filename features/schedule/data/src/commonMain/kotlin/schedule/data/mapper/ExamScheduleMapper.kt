package schedule.data.mapper

import schedule.data.entity.ExamDetailsSchema
import schedule.data.entity.ExamScheduleSchema
import schedule.domain.model.ExamDetailsModel
import schedule.domain.model.ExamScheduleModel

class ExamScheduleMapper {

    // Convert ExamScheduleSchema to ExamScheduleModel
    fun fromSchemaToModel(schema: ExamScheduleSchema): ExamScheduleModel {
        return ExamScheduleModel(
            dept = schema.dept,
            session = schema.session,
            year = schema.year,
            semester = schema.semester,
            exams = schema.exams.map { fromExamDetailsSchemaToModel(it) } // Convert each ExamDetailsSchema to ExamDetailsModel
        )
    }

    // Convert ExamScheduleModel to ExamScheduleSchema
    fun fromModelToSchema(model: ExamScheduleModel): ExamScheduleSchema {
        return ExamScheduleSchema(
            dept = model.dept,
            session = model.session,
            year = model.year,
            semester = model.semester,
            exams = model.exams.map { fromExamDetailsModelToSchema(it) } // Convert each ExamDetailsModel to ExamDetailsSchema
        )
    }

    // Helper to convert ExamDetailsSchema to ExamDetailsModel
    private fun fromExamDetailsSchemaToModel(schema: ExamDetailsSchema): ExamDetailsModel {
        return ExamDetailsModel(
            courseCode = schema.courseCode,
            courseTitle = schema.courseTitle,
            time = schema.time,
            date = schema.date
        )
    }

    // Helper to convert ExamDetailsModel to ExamDetailsSchema
    private fun fromExamDetailsModelToSchema(model: ExamDetailsModel): ExamDetailsSchema {
        return ExamDetailsSchema(
            courseCode = model.courseCode,
            courseTitle = model.courseTitle,
            time = model.time,
            date = model.date
        )
    }
}
