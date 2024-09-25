package database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import database.dao.CredentialDao
import database.dao.DepartmentDao
import database.dao.FacultyDao
import database.dao.FacultyMemberDao
import database.schema.CredentialSchema
import database.schema.DepartmentConverter
import database.schema.FacultyMemberSchema
import database.schema.DepartmentSchema
import database.schema.FacultySchema

@Database(
    entities = [
        CredentialSchema::class,
        FacultySchema::class,
        DepartmentSchema::class,
        FacultyMemberSchema::class,

    ],
    version = 1
)
@TypeConverters(DepartmentConverter::class)
internal abstract class Database : RoomDatabase() {
    abstract fun credentialDao(): CredentialDao
    abstract fun facultyDao(): FacultyDao
    abstract fun departmentDao(): DepartmentDao
    abstract fun facultyMemberDao(): FacultyMemberDao

}