package administration.data.factory

import admin_office.domain.model.AdminOfficerModel
import admin_office.domain.model.OfficeModel
import admin_office.domain.model.SubOfficeModel
import administration.data.PackageLevelAccess
import administration.data.entity.AdminOfficerEntity
import administration.data.entity.OfficeEntity
import administration.data.entity.SubOfficeEntity

@OptIn(PackageLevelAccess::class)
internal object Mapper {

    fun officeEntityToModel(entities: List<OfficeEntity>): List<OfficeModel> {
        return entities.map {
            OfficeModel(
                officeId = it.office_id,
                name = it.name,
                subOfficeCount = it.sub_offices_count
            )

        }
    }

    fun subOfficeEntityToModel(entity: List<SubOfficeEntity>): List<SubOfficeModel> {
        return entity.map {
            SubOfficeModel(
                subOfficeId = it.sub_office_id,
                name = it.name,
                employeeCount = it.office_members_count
            )

        }
    }
    fun  adminOfficersEntityToModel(entity: List<AdminOfficerEntity>): List<AdminOfficerModel> {
        return entity.map {
            AdminOfficerModel(
                name = it.name,
                email = it.email,
                additionalEmail = it.additional_email,
                phone = it.phone,
                profileImage =it.offices.firstOrNull()?.designation ?:"",
                achievements = it.achievement,
                designations = it.offices.firstOrNull()?.designation ?:"",
                roomNo = it.offices.firstOrNull()?.room_no ?:"",
            )
        }
    }
}
