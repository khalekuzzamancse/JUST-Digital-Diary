package core.database.api

import core.data.entity.administration.AdminOfficerReadEntity
import core.data.entity.administration.OfficeReadEntity
import core.data.entity.administration.SubOfficeReadEntity

interface AdministrationApiFacade {
    /**Throw custom Exception where the Exception can be server feedback*/
    suspend fun readOfficesOrThrow():List<OfficeReadEntity>
    /**Throw custom Exception where the Exception can be server feedback*/
    suspend fun readSubOfficesOrThrow(officeId:String):List<SubOfficeReadEntity>
    /**Throw custom Exception where the Exception can be server feedback*/
    suspend fun readEmployeesOrThrow(subOfficeId:String):List<AdminOfficerReadEntity>
}