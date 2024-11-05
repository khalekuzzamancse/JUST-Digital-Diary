package domain.api
import core.data.entity.administration.AdminOfficerReadEntity
import core.data.entity.administration.OfficeReadEntity
import core.data.entity.administration.SubOfficeReadEntity

/**
 * Must match the primary key such FacultyId,deptId,TeacherId, etc with remote
 * ReadEntity that is why taking read entity,if not match or use own primary key or
 * faculty id,deptId,teacherId that does not match with remote then though data will
 * store to cache but can not retrieve because of query parameter not match,
 * If we create a separate entity for that this will gain some extra code and need to write the mapper and etc,instead of
 * just passing the remote response then use all relevant field for caching
 */
interface AdministrationCacheApi {
    suspend fun readOfficesOrThrow(): List<OfficeReadEntity>
    suspend fun insertOfficesOrThrow(entities: List<OfficeReadEntity>)
    suspend fun insertSubOfficesOrThrow(officeId: String, entities: List<SubOfficeReadEntity>)
    suspend fun readSubOfficesOrThrow(officeId: String): List<SubOfficeReadEntity>
    suspend fun insertEmployeeOrThrow(subOfficeId: String, entities: List<AdminOfficerReadEntity>)
    suspend fun readEmployeeOrThrow(subOfficeId: String): List<AdminOfficerReadEntity>
}