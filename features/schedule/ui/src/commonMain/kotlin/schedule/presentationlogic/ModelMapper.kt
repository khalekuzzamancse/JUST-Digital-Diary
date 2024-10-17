@file:Suppress("functionName", "unused")

package schedule.presentationlogic

import schedule.domain.model.ClassScheduleWriteModel
import schedule.presentationlogic.model.ClassDetailModel
import schedule.domain.model.ClassDetailModel as DomainClassDetailModel
import schedule.presentationlogic.model.ClassModel
import schedule.domain.model.ClassModel as DomainClassModel
import schedule.presentationlogic.model.ClassScheduleModel
import schedule.domain.model.ClassScheduleReadModel as DomainClassScheduleReadModel


internal object ModelMapper {
    fun DomainClassScheduleReadModel.toModel() = ClassScheduleModel(
        deptName = "$deptName($deptShortname)",
        year = year,
        semester = semester,
        session = session,
        routine = routine.map { it._toModel() }
    )

    fun ClassScheduleModel.toModel() = ClassScheduleWriteModel(
        year = year,
        semester = semester,
        session = session,
        routine = routine.map { it.toModel() }
    )



    //TODO:Helper function

    private fun DomainClassModel._toModel() = ClassModel(
        day = day,
        items = classes.map { it._toModel() }
    )

    private fun DomainClassDetailModel._toModel() = ClassDetailModel(
        courseCode = subject,
        time = time,
        teacherName = teacherName,
        durationInHours = 1//TODO: Duration is derived property fix it later
    )


    private fun ClassModel.toModel() = DomainClassModel(
        day = day,
        classes = items.map { it.toModel() }
    )

    private fun ClassDetailModel.toModel() = DomainClassDetailModel(
        subject = courseCode,
        time = time,
        teacherName = teacherName,
    )


}