package academic.presenter

import academic.model.Dept
import faculty.domain.model.DepartmentModel
import faculty.domain.model.FacultyModel
import faculty.domain.model.TeacherModel

private typealias UiFacultyModel = academic.model.FacultyModel
private typealias UiDepartmentModel = academic.model.DepartmentModel
private typealias TeacherUiModel = academic.model.TeacherModel

internal object Presenter {
    fun toUiFacultyModel(model: FacultyModel) = UiFacultyModel(
        name = model.name,
        id = model.facultyId,
        numberOfDepartment = "${model.departmentsCount}"
    )

    fun toUiFacultyModel(model: DepartmentModel) = UiDepartmentModel(
        name = model.name,
        id = model.id.toString(),
        shortName = model.shortName
    )

    fun toTeacherUiModel(model: TeacherModel) = TeacherUiModel(
        name = model.name,
        email = model.email,
        additionalEmail = model.additionalEmail,
        phone = model.phone,
        achievements = model.achievements,
        designations = model.designations,
        dept = Dept(
            model.deptName,
            model.deptSortName
        ),
        roomNo = model.roomNo
    )
}