package database.local.api

import database.local.DB
import database.local.schema.administration.OfficeEntityLocal
import database.local.schema.administration.OfficeSchema
import database.local.schema.administration.AdminOfficerEntityLocal
import database.local.schema.administration.AdminOfficerSchema
import database.local.schema.administration.SubOfficeEntityLocal
import database.local.schema.administration.SubOfficeSchema
import io.realm.kotlin.ext.query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

object AdministrationAPIs {

    private val db = DB.db

    suspend fun addAdminOffice(model: OfficeEntityLocal):Result<OfficeEntityLocal> {
        return DB.addEntity(OfficeSchema().apply {
             this.id = model.id
             this.officeId = model.officeId
             this.name = model.name
             this.subOfficesCount = model.subOfficesCount
         }) { this.toEntity() }
    }

    suspend fun addAdminOffices(models: List<OfficeEntityLocal>) {
        models.map { model -> CoroutineScope(Dispatchers.Default).async { addAdminOffice(model) } }
            .awaitAll()
    }

    // SubOffice Related APIs
    suspend fun addSubOffice(model: SubOfficeEntityLocal) =
        DB.addEntity(SubOfficeSchema().apply {
            this.serialNo = model.serialNo
            this.officeId = model.officeId
            this.name = model.name
            this.officeMembersCount = model.officeMembersCount
        }) { this.toEntity() }

    suspend fun addSubOffices(models: List<SubOfficeEntityLocal>) {
        models.map { model -> CoroutineScope(Dispatchers.Default).async { addSubOffice(model) } }
            .awaitAll()
    }

    // Officer Related APIs
    suspend fun addOfficer(model: AdminOfficerEntityLocal) =
        DB.addEntity(AdminOfficerSchema().apply {
            this.officeId=model.officeId
            this.uid = model.uid
            this.name = model.name
            this.email = model.email
            this.additionalEmail = model.additionalEmail
            this.profileImage = model.profileImage
            this.achievement = model.achievement
            this.phone = model.phone
            this.designations = model.designations
            this.roomNo = model.roomNo
        }) { this.toEntity() }

    suspend fun addOfficers(models: List<AdminOfficerEntityLocal>) {
        models.map { model -> CoroutineScope(Dispatchers.Default).async { addOfficer(model) } }
            .awaitAll()
    }

    // Retrieve APIs for AdminOffice, SubOffice, and Officers
    fun retrieveAdminOffices(): Result<List<OfficeEntityLocal>> {
        return DB.retrieveEntities(
            query = { db.query<OfficeSchema>().find() },
            transform = OfficeSchema::toEntity
        )
    }


    fun retrieveSubOffices(officeId: String): Result<List<SubOfficeEntityLocal>> {
        return  DB.retrieveEntities(
            query = { db.query<SubOfficeSchema>("officeId=$0", officeId).find() },
            transform = SubOfficeSchema::toEntity
        )
    }

    fun retrieveOfficers(officeId: String): Result<List<AdminOfficerEntityLocal>> {
        return  DB.retrieveEntities(
            query = { db.query<AdminOfficerSchema>("officeId=$0", officeId).find() },
            transform = AdminOfficerSchema::toEntity
        )
    }
}
