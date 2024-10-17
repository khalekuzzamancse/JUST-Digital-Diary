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



    // Helper to convert ExamDetailsSchema to ExamDetailsModel
    private fun fromExamDetailsSchemaToModel(schema: ExamDetailsSchema): ExamDetailsModel {
        return ExamDetailsModel(
            courseCode = schema.courseCode,
            courseTitle = schema.courseTitle,
            time = schema.time,
            date = schema.date
        )
    }


}
