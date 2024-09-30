package database.factory

import database.apis.AcademicApi
import database.apis.AuthApi

expect class DatabaseFactory {
    fun createAcademicApi():AcademicApi
    fun createAuthApi():AuthApi
}