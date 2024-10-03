package core.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import core.database.schema.CredentialSchema
import core.database.schema.DepartmentConverter
import core.database.schema.DepartmentSchema
import core.database.schema.FacultyMemberSchema
import core.database.schema.FacultySchema
import core.database.schema.TokenSchema

@Database(
    entities = [
        CredentialSchema::class,
        FacultySchema::class,
        DepartmentSchema::class,
        FacultyMemberSchema::class,
        TokenSchema::class

    ],
    version = 1
)

@TypeConverters(DepartmentConverter::class)
internal abstract class Database : RoomDatabase() {
    abstract fun credentialDao(): core.database.dao.CredentialDao
    abstract fun facultyDao(): core.database.dao.FacultyDao
    abstract fun departmentDao(): core.database.dao.DepartmentDao
    abstract fun facultyMemberDao(): core.database.dao.FacultyMemberDao
    abstract fun tokenDao(): core.database.dao.TokenDao

}