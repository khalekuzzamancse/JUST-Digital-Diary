package core.roomdb.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import core.roomdb.dao.CredentialDao
import core.roomdb.dao.DepartmentDao
import core.roomdb.dao.FacultyDao
import core.roomdb.dao.FacultyMemberDao
import core.roomdb.dao.TokenDao
import core.roomdb.schema.CredentialSchema
import core.roomdb.schema.DepartmentConverter
import core.roomdb.schema.DepartmentSchema
import core.roomdb.schema.FacultyMemberSchema
import core.roomdb.schema.FacultySchema
import core.roomdb.schema.TokenSchema

@Database(entities = [CredentialSchema::class, FacultySchema::class, DepartmentSchema::class,
        FacultyMemberSchema::class, TokenSchema::class], version = 1
)
@TypeConverters(DepartmentConverter::class)
internal abstract class DB : RoomDatabase() {
    abstract fun credentialDao(): CredentialDao
    abstract fun facultyDao(): FacultyDao
    abstract fun departmentDao(): DepartmentDao
    abstract fun facultyMemberDao(): FacultyMemberDao
    abstract fun tokenDao(): TokenDao

}