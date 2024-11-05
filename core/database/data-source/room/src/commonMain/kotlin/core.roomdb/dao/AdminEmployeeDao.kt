@file:Suppress("unused")
package core.roomdb.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import core.roomdb.schema.AdminOfficerSchema


@Dao
internal interface AdminEmployeeDao {
    @Upsert
    suspend fun upset(schemas: List<AdminOfficerSchema>)

    @Query("SELECT * FROM admin_employee")
    suspend fun readAll(): List<AdminOfficerSchema>

    @Query("SELECT * FROM admin_employee WHERE sub_office_id= :officeId")
    suspend fun read(officeId: String): List<AdminOfficerSchema>
}
