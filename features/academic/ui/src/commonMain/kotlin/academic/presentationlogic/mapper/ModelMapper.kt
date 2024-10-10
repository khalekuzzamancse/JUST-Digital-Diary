package academic.presentationlogic.mapper

import academic.presentationlogic.model.public_.Dept
import faculty.domain.model.public_.DepartmentModel
import faculty.domain.model.public_.FacultyModel
import faculty.domain.model.public_.TeacherModel

private typealias UiFacultyModel = academic.presentationlogic.model.public_.FacultyModel
private typealias UiDepartmentModel = academic.presentationlogic.model.public_.DepartmentModel
private typealias TeacherUiModel = academic.presentationlogic.model.public_.TeacherModel

internal object ModelMapper {
    fun toUiFacultyModel(model: FacultyModel) = UiFacultyModel(
        name = model.name,
        id = model.facultyId,
        numberOfDepartment = "${model.departmentsCount}"
    )

    fun toUiFacultyModel(model: DepartmentModel) =
        UiDepartmentModel(
            name = model.name,
            id = model.deptId,
            shortName = model.shortName,
            membersCount = model.employeeCount.toString()
        )

    fun toTeacherUiModel(model: TeacherModel) = TeacherUiModel(
        name = model.name,
        email = model.email,
        additionalEmail = model.additionalEmail ?: "",
        phone = model.phone,
        achievements = model.achievement,
        profileImageLink = model.profile,
        dept = Dept(
            model.departments.firstOrNull()?.name ?: "",
            model.departments.firstOrNull()?.shortname ?: "",
        ),
        roomNo = model.departments.firstOrNull()?.roomNo ?: "",
        designations = model.departments.firstOrNull()?.designation ?: "",
    )
}