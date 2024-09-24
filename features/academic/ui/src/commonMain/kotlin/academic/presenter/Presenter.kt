package academic.presenter

import faculty.domain.model.DepartmentModel
import faculty.domain.model.FacultyModel

typealias UiFacultyModel = academic.model.FacultyModel
typealias UiDepartmentModel = academic.model.DepartmentModel

object Presenter {
    fun toUiFacultyModel(model:FacultyModel) = UiFacultyModel(
        name = model.name,
        id = model.facultyId,
        numberOfDepartment = "${model.departmentsCount}"
    )
    fun toUiFacultyModel(model:DepartmentModel) = UiDepartmentModel(
        name = model.name,
        id = model.id.toString(),
        shortName = model.shortName
    )
}