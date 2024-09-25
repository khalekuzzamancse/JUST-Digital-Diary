package database.factory

import database.apis.AcademicApi

expect class DatabaseFactory {
    fun createAcademicApi():AcademicApi
}