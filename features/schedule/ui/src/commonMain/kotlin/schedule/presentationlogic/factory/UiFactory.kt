@file:Suppress("unused", "functionName")

package schedule.presentationlogic.factory

import schedule.di.DiFactory
import schedule.presentationlogic.factory.class_schedule.AddCommand
import schedule.presentationlogic.factory.class_schedule.AddCommandImpl
import schedule.presentationlogic.factory.class_schedule.ClassScheduleInsertControllerImpl
import schedule.presentationlogic.factory.class_schedule.ValidatorImpl
import schedule.presentationlogic.factory.exam_schedule.ExamScheduleControllerImpl
import schedule.presentationlogic.controller.ClassScheduleInsertController
import schedule.presentationlogic.controller.ClassScheduleViewerController
import schedule.presentationlogic.controller.ExamScheduleInsertController
import schedule.presentationlogic.controller.core.AcademicInfoController
import schedule.presentationlogic.factory.core.AcademicInfoControllerImpl
import schedule.presentationlogic.factory.core.AcademicInfoValidatorImpl

typealias ExamScheduleValidator = schedule.presentationlogic.factory.exam_schedule.ValidatorImpl
typealias ClassScheduleAddCommand = AddCommandImpl

object UiFactory {
    fun createClassScheduleFormController(): ClassScheduleInsertController =
        ClassScheduleInsertControllerImpl(
            validator = _createValidator(),
            addCommand = _createAddCommand(),
            academicController = _academicFormController(),
            insertUseCase = DiFactory.insertUseCase()
        )

    fun classScheduleViewerController(): ClassScheduleViewerController =
        ClassScheduleViewerControllerImpl(
            readAllUseCase = DiFactory.createRetrieveClassScheduleUseCase()
        )

    fun createExamScheduleFormController(): ExamScheduleInsertController =
        ExamScheduleControllerImpl(
            validator = schedule.presentationlogic.factory.ExamScheduleValidator(),
            addCommand = schedule.presentationlogic.factory.exam_schedule.AddCommandImpl()
        )

    private fun _createValidator(): ClassScheduleInsertController.Validator = ValidatorImpl()

    private fun _createAddCommand(): AddCommand =
        schedule.presentationlogic.factory.ClassScheduleAddCommand()

    private fun _academicFormController(): AcademicInfoController = AcademicInfoControllerImpl(
        validator = AcademicInfoValidatorImpl(),
        allDeptUseCase = DiFactory.readAllDeptUseCase()
    )
}