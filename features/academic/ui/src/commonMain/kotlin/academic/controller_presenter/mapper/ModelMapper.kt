package academic.controller_presenter.mapper

import academic.controller_presenter.model.Dept
import faculty.domain.model.DepartmentModel
import faculty.domain.model.FacultyModel
import faculty.domain.model.TeacherModel

private typealias UiFacultyModel = academic.controller_presenter.model.FacultyModel
private typealias UiDepartmentModel = academic.controller_presenter.model.DepartmentModel
private typealias TeacherUiModel = academic.controller_presenter.model.TeacherModel

internal object ModelMapper {
    fun toUiFacultyModel(model: FacultyModel) = academic.controller_presenter.mapper.UiFacultyModel(
        name = model.name,
        id = model.facultyId,
        numberOfDepartment = "${model.departmentsCount}"
    )

    fun toUiFacultyModel(model: DepartmentModel) =
        academic.controller_presenter.mapper.UiDepartmentModel(
            name = model.name,
            id = model.id.toString(),
            shortName = model.shortName
        )

    fun toTeacherUiModel(model: TeacherModel) = academic.controller_presenter.mapper.TeacherUiModel(
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