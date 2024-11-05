@file:Suppress("unused")
package core.roomdb.dao

import androidx.room.*
import core.roomdb.schema.SubOfficeSchema


@Dao
internal interface SubOfficeDao {
    @Upsert
    suspend fun upsert(schemas: List<SubOfficeSchema>)

    @Query("SELECT * FROM sub_office_table")
    suspend fun readAll(): List<SubOfficeSchema>

    @Query("SELECT * FROM sub_office_table WHERE officeId= :officeId")
    suspend fun read(officeId: String): List<SubOfficeSchema>
}
