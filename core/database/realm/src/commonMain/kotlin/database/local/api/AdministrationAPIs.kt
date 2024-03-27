package database.local.api

import database.local.DB
import database.local.schema.administration.AdminOfficeEntityLocal
import database.local.schema.administration.AdminOfficeSchema
import database.local.schema.administration.AdminOfficerEntityLocal
import database.local.schema.administration.AdminOfficerSchema
import database.local.schema.administration.AdminSubOfficeEntityLocal
import database.local.schema.administration.AdminSubOfficeSchema
import io.realm.kotlin.ext.query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

object AdministrationAPIs {

    private val db = DB.db

    suspend fun addAdminOffice(model: AdminOfficeEntityLocal):Result<AdminOfficeEntityLocal> {
        return DB.addEntity(AdminOfficeSchema().apply {
             this.id = model.id
             this.officeId = model.officeId
             this.name = model.name
             this.subOfficesCount = model.subOfficesCount
         }) { this.toEntity() }
    }

    suspend fun addAdminOffices(models: List<AdminOfficeEntityLocal>) {
        models.map { model -> CoroutineScope(Dispatchers.Default).async { addAdminOffice(model) } }
            .awaitAll()
    }

    // SubOffice Related APIs
    suspend fun addSubOffice(model: AdminSubOfficeEntityLocal) =
        DB.addEntity(AdminSubOfficeSchema().apply {
            this.id = model.id
            this.subOfficeId = model.subOfficeId
            this.name = model.name
            this.officeMembersCount = model.officeMembersCount
        }) { this.toEntity() }

    suspend fun addSubOffices(models: List<AdminSubOfficeEntityLocal>) {
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
    fun retrieveAdminOffices(): Result<List<AdminOfficeEntityLocal>> {
        return DB.retrieveEntities(
            query = { db.query<AdminOfficeSchema>().find() },
            transform = AdminOfficeSchema::toEntity
        )
    }


    fun retrieveSubOffices(officeId: String): Result<List<AdminSubOfficeEntityLocal>> {
        return  DB.retrieveEntities(
            query = { db.query<AdminSubOfficeSchema>("officeId=$0", officeId).find() },
            transform = AdminSubOfficeSchema::toEntity
        )
    }

    fun retrieveOfficers(officeId: String): Result<List<AdminOfficerEntityLocal>> {
        return  DB.retrieveEntities(
            query = { db.query<AdminOfficerSchema>("officeId=$0", officeId).find() },
            transform = AdminOfficerSchema::toEntity
        )
    }
}
