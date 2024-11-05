package core.roomdb.factory

import android.content.Context
import android.widget.Button
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import core.roomdb.db.DB

internal fun getContext(
    block: @Composable (Context) -> DB,
) {


}


internal fun getDatabase(context: Context): DB {
    val dbFile = context.getDatabasePath("just_diary.db")
    return Room.databaseBuilder<DB>(context = context, name = dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver())
        .fallbackToDestructiveMigration(dropAllTables = true)
        .build()

}

/*
  * When schema changed we need destroy the database  using fallbackToDestructiveMigration as well as the file
  * Ensure the database file is deleted before creating a new one in case of schema change
  */
//Run this when want to delete old file,but every time running will  clear the database in each query
//    if (dbFile.exists()) {
//        dbFile.delete()
//    }
