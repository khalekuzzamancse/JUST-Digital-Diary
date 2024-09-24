@file:Suppress("unused","functionName")
package schedule.ui.factory

import schedule.ui.factory.class_schedule.AddCommand
import schedule.ui.factory.class_schedule.ClassScheduleFormControllerImpl
import schedule.ui.factory.class_schedule.ValidatorImpl
import schedule.ui.factory.exam_schedule.ExamScheduleFormControllerImpl
import schedule.ui.ui.admin.add_class_schedule.ClassScheduleFormController
import schedule.ui.ui.admin.add_exam_schedule.ExamScheduleFormController

typealias ExamScheduleValidator = schedule.ui.factory.exam_schedule.ValidatorImpl
typealias ClassScheduleAddCommand=schedule.ui.factory.class_schedule.AddCommandImpl

object UiFactory {
    fun createClassScheduleFormController(): ClassScheduleFormController =
        ClassScheduleFormControllerImpl(
            validator = _createValidator(),
            addCommand = _createAddCommand()
        )
    fun createExamScheduleFormController(): ExamScheduleFormController =
        ExamScheduleFormControllerImpl(
            validator = ExamScheduleValidator(),
            addCommand = schedule.ui.factory.exam_schedule.AddCommandImpl()
        )

    private fun _createValidator(): ClassScheduleFormController.Validator = ValidatorImpl()

    private fun _createAddCommand(): AddCommand =ClassScheduleAddCommand()

}