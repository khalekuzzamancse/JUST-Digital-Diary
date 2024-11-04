package administration.data
import admin_office.domain.model.AdminOfficerModel
import admin_office.domain.model.OfficeModel
import admin_office.domain.model.SubOfficeModel
import core.data.entity.administration.AdminOfficerReadEntity
import core.data.entity.administration.OfficeReadEntity
import core.data.entity.administration.SubOfficeReadEntity

internal object Mapper {
    fun officeEntityToModel(entities: List<OfficeReadEntity>): List<OfficeModel> {
        return entities.map {
            OfficeModel(
                officeId = it.officeId,
                name = it.name,
                subOfficeCount = it.sub_offices_count
            )

        }
    }


    fun subOfficeEntityToModel(entity: List<SubOfficeReadEntity>): List<SubOfficeModel> {
        return entity.map {
            SubOfficeModel(
                subOfficeId = it.sub_office_id,
                name = it.name,
                employeeCount = it.office_members_count
            )

        }
    }

    fun  adminOfficersEntityToModel(entity: List<AdminOfficerReadEntity>): List<AdminOfficerModel> {
        return entity.map {
            AdminOfficerModel(
                name = it.name,
                email = it.email,
                additionalEmail = it.additional_email,
                phone = it.phone,
                profileImage =it.profile,
                achievements = it.achievement,
                designations = it.designation,
                roomNo = it.room_no,
            )
        }
    }
}
