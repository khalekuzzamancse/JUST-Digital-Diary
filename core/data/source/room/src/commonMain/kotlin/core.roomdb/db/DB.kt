package core.roomdb.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import core.roomdb.dao.AdminEmployeeDao
import core.roomdb.dao.DepartmentDao
import core.roomdb.dao.FacultyDao
import core.roomdb.dao.FacultyMemberDao
import core.roomdb.dao.OfficeDao
import core.roomdb.dao.SubOfficeDao
import core.roomdb.schema.AdminOfficerSchema
import core.roomdb.schema.DepartmentConverter
import core.roomdb.schema.DepartmentSchema
import core.roomdb.schema.FacultyMemberSchema
import core.roomdb.schema.FacultySchema
import core.roomdb.schema.OfficeSchema
import core.roomdb.schema.SubOfficeSchema

@Database(
    entities = [
        FacultySchema::class, DepartmentSchema::class, FacultyMemberSchema::class,
        OfficeSchema::class, SubOfficeSchema::class, AdminOfficerSchema::class],
    version = 1
)
@TypeConverters(DepartmentConverter::class)
internal abstract class DB : RoomDatabase() {
    abstract fun facultyDao(): FacultyDao
    abstract fun departmentDao(): DepartmentDao
    abstract fun facultyMemberDao(): FacultyMemberDao
    abstract fun officeDao():OfficeDao
    abstract fun  subOfficeDao():SubOfficeDao
    abstract  fun adminEmployeeDao():AdminEmployeeDao
}