package schedule.ui.factory

import schedule.ui.admin.ClassScheduleFormController
import schedule.ui.factory.class_schedule.AddCommand
import schedule.ui.factory.class_schedule.AddCommandImpl
import schedule.ui.factory.class_schedule.ClassScheduleFormControllerImpl
import schedule.ui.factory.class_schedule.ValidatorImpl

object Factory {
    fun createClassScheduleFormController(): ClassScheduleFormController =
        ClassScheduleFormControllerImpl(
            validator = createValidator(),
            addCommand = createAddCommand()
        )

    private fun createValidator(): ClassScheduleFormController.Validator = ValidatorImpl()

    private fun createAddCommand(): AddCommand = AddCommandImpl()
}