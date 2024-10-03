package administration.ui.public_

import admin_office.domain.model.AdminOfficerModel
import admin_office.domain.model.OfficeModel
import admin_office.domain.model.SubOfficeModel


typealias UiOfficeModel = administration.controller_presenter.model.OfficeModel
typealias UiSubOfficeModel = administration.controller_presenter.model.SubOfficeModel
typealias UiAdminOfficerModel=administration.controller_presenter.model.AdminOfficerModel


object Mapper {
    fun toUiModel(model: OfficeModel): UiOfficeModel {
        return UiOfficeModel(
            name = model.name,
            id = model.officeId,
            numberOfSubOffices = model.subOfficeCount.toString()
        )
    }

    fun toUiModel(model: SubOfficeModel): UiSubOfficeModel {
        return UiSubOfficeModel(
            name = model.name,
            id = model.subOfficeId,
            employeeCnt = model.employeeCount.toString()
        )
    }
    fun toUiModel(model: AdminOfficerModel): UiAdminOfficerModel {
        return UiAdminOfficerModel(
            name = model.name,
            email = model.email,
            additionalEmail = model.additionalEmail ?: "",
            profileImageLink = model.profileImage,
            achievements = model.achievements,
            designations = model.designations,
            phone = model.phone ?: "",
            roomNo = model.roomNo
        )
    }
}