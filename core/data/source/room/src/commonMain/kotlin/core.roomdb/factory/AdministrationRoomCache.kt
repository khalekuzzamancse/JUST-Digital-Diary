package core.roomdb.factory

import core.data.entity.administration.AdminOfficerReadEntity
import core.data.entity.administration.OfficeReadEntity
import core.data.entity.administration.SubOfficeReadEntity
import core.roomdb.dao.AdminEmployeeDao
import core.roomdb.dao.OfficeDao
import core.roomdb.dao.SubOfficeDao
import core.roomdb.schema.AdminOfficerSchema
import core.roomdb.schema.OfficeSchema
import core.roomdb.schema.SubOfficeSchema
import domain.api.AdministrationCacheApi

class AdministrationRoomCache internal constructor(
    private val officeDao: OfficeDao,
    private val subOfficeDao: SubOfficeDao,
    private val employeeDao: AdminEmployeeDao,
) : AdministrationCacheApi {
    override suspend fun readOfficesOrThrow(): List<OfficeReadEntity> {
        return officeDao
            .readAll()
            .map { schema ->
                OfficeReadEntity(
                    officeId = schema.officeId,
                    priority = schema.priority,
                    name = schema.name,
                    sub_offices_count = 0 //TODO:Derived property, refactor later
                )
            }
    }

    override suspend fun insertOfficesOrThrow(entities: List<OfficeReadEntity>) {
        officeDao.upsert(
            entities.map { entity ->
                OfficeSchema(
                    officeId = entity.officeId,
                    priority = entity.priority,
                    name = entity.name,
                )
            }
        )
    }

    override suspend fun insertSubOfficesOrThrow(
        officeId: String,
        entities: List<SubOfficeReadEntity>
    ) {
        subOfficeDao.upsert(
            entities.map { entity ->
                SubOfficeSchema(
                    priority = entity.priority,
                    officeId = officeId,
                    sub_office_id = entity.sub_office_id,
                    name = entity.name,
                )
            }
        )
    }

    override suspend fun readSubOfficesOrThrow(officeId: String): List<SubOfficeReadEntity> {
        return subOfficeDao
            .read(officeId)
            .map { schema ->
                SubOfficeReadEntity(
                    priority = schema.priority,
                    sub_office_id = schema.sub_office_id,
                    name = schema.name,
                    office_members_count = 0 //TODO:Derived property, refactor later
                )
            }
    }

    override suspend fun insertEmployeeOrThrow(
        subOfficeId: String,
        entities: List<AdminOfficerReadEntity>
    ) {
        employeeDao
            .upset(
                entities.map { schema ->
                    AdminOfficerSchema(
                        id = schema.id,
                        sub_office_id = subOfficeId,
                        priority = schema.priority,
                        name = schema.name,
                        email = schema.email,
                        additional_email = schema.additional_email,
                        phone = schema.phone,
                        achievement = schema.achievement,
                        profile = schema.profile,
                        designation = schema.designation,
                        room_no = schema.room_no
                    )
                }
            )

    }

    override suspend fun readEmployeeOrThrow(subOfficeId: String): List<AdminOfficerReadEntity> {
        return employeeDao
            .read(subOfficeId)
            .map { schema ->
                AdminOfficerReadEntity(
                    id = schema.id,
                    priority = schema.priority,
                    name = schema.name,
                    email = schema.email,
                    additional_email = schema.additional_email,
                    phone = schema.phone,
                    achievement = schema.achievement,
                    profile = schema.profile,
                    designation = schema.designation,
                    room_no = schema.room_no
                )
            }
    }
}