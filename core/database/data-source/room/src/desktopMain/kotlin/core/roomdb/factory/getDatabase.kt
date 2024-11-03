package core.roomdb.factory

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import core.roomdb.db.DB
import java.io.File
 internal fun getDatabase(): DB {

    val dbFile = File(System.getProperty("java.io.tmpdir"), "database.db")
    /*
     * When schema changed we need destroy the database  using fallbackToDestructiveMigration as well as the file
     * Ensure the database file is deleted before creating a new one in case of schema change
     */
//    if (dbFile.exists()) {
//        dbFile.delete()
//    }

    return Room.databaseBuilder<DB>(name = dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver())
//        .fallbackToDestructiveMigration(dropAllTables = true)
        .build()
}