@file:Suppress("UnUsed")
package schedule.ui.factory

import schedule.ui.ui.admin.ClassScheduleFormController
import schedule.ui.ui.admin.ExamScheduleFormController
import schedule.ui.factory.class_schedule.AddCommand
import schedule.ui.factory.class_schedule.AddCommandImpl
import schedule.ui.factory.class_schedule.ClassScheduleFormControllerImpl
import schedule.ui.factory.class_schedule.ValidatorImpl
import schedule.ui.factory.exam_schedule.AddExamToScheduleCommandImpl
import schedule.ui.factory.exam_schedule.ExamScheduleFormControllerImpl
typealias ExamScheduleValidator = schedule.ui.factory.exam_schedule.ValidatorImpl

object UiFactory {
    fun createClassScheduleFormController(): ClassScheduleFormController =
        ClassScheduleFormControllerImpl(
            validator = createValidator(),
            addCommand = createAddCommand()
        )
    fun createExamScheduleFormController(): ExamScheduleFormController =
        ExamScheduleFormControllerImpl(
            validator = ExamScheduleValidator(),
            addCommand = AddExamToScheduleCommandImpl()
        )

    private fun createValidator(): ClassScheduleFormController.Validator = ValidatorImpl()

    private fun createAddCommand(): AddCommand = AddCommandImpl()

}