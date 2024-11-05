@file:Suppress("unused")
package core.roomdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import core.roomdb.schema.FacultySchema
import core.roomdb.schema.OfficeSchema

@Dao
internal interface OfficeDao {
    @Upsert
    suspend fun upsert(schemas: List<OfficeSchema>)

    @Query("SELECT * FROM office_table")
    suspend fun readAll(): List<OfficeSchema>

}
