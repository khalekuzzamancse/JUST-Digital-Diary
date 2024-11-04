@file:Suppress("functionName")

package administration.data

import admin_office.domain.repository.Repository


object DataFactory {
    fun createRepository(token: String?): Repository = RepositoryImpl(token)
}