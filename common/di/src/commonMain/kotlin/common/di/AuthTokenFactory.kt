package common.di

import common.data.token.TokenRetriever

/**
 * Keeping single source of truth for auth token,since this token will be
 * used by many other modules.
 * so if we need to change the token retrieve logic or the source or something else
 * then the client code does not need to change.
 * this factory pattern also will give loose coupling since the instance is creating
 * in a single place.
 *
 */
object AuthTokenFactory {
    suspend fun retrieveToken()= TokenRetriever()
        .tokenRequest("smsouav","test@123")
}