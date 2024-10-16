@file:Suppress("functionName","unused")
package schedule.data.mapper

import schedule.data.entity.schedule.ClassDetailEntity
import schedule.data.entity.schedule.ClassEntity
import schedule.data.entity.schedule.ClassScheduleReadEntity
import schedule.data.entity.schedule.ClassScheduleWriteEntity
import schedule.domain.model.ClassDetailModel
import schedule.domain.model.ClassModel
import schedule.domain.model.ClassScheduleReadModel
import schedule.domain.model.ClassScheduleWriteModel

/** Mapping from Read entity to domain Read model*/
internal object ReadEntityModelMapper {
    fun ClassScheduleReadEntity.toModel()= ClassScheduleReadModel(
        id=id,
        deptName=deptName,
        deptShortname = deptShortName,
        year = year,
        semester = semester,
        session = session,
        routine = routine.map { it._toModel() }
    )
    fun ClassScheduleWriteModel.toEntity() = ClassScheduleWriteEntity(
        year = year,
        semester = semester,
        session = session,
        routine = routine.map { it.toEntity() }
    )


    private fun ClassEntity._toModel() = ClassModel(
        day = day,
        classes =items.map { it._toModel() }
    )
    private fun ClassDetailEntity._toModel() = ClassDetailModel(
        subject = subject,
        time = time,
        teacherName = teacher,
    )

    private fun ClassModel.toEntity() = ClassEntity(
        day = day,
        items = classes.map { it.toEntity() }
    )

    private fun ClassDetailModel.toEntity() = ClassDetailEntity(
        subject = subject,
        time = time,
        teacher = teacherName,
    )

}