package core.roomdb.factory

import android.content.Context
import android.util.Log
import core.roomdb.db.DB

object RoomDBProvider {
     internal lateinit var db: DB
        private set

    /**
     * Initialize the database form android
     */
    fun initializeDB(context:Context){
        db= getDatabase(context)
        Log.d("InitializesROOMDB",":$db")
    }
    
}