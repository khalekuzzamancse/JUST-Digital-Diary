package com.just.cse.digitaldiary.twozerotwothree.core.local_database.realm.authentication.other

import com.just.cse.digitaldiary.twozerotwothree.core.local_database.realm.RealmDatabase
import kotlinx.coroutines.runBlocking

fun main() {
//    runBlocking {
//        RealmDatabase.saveSignInInfo(
//            username ="khalek02",
//            password ="test@123"
//        )
//    }
//    runBlocking {
//        RealmDatabase.getSingedInUserInfo()
//    }
        runBlocking {
        RealmDatabase.getSingedInUserInfo()
    }
}