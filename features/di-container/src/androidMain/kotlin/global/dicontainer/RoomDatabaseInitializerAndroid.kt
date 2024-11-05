package global.dicontainer;

import android.content.Context;
import core.roomdb.factory.RoomDBProvider

fun initializeDBAndroid(context:Context) {
    RoomDBProvider.initializeDB(context)
}