package domain.repository

import domain.model.ProfileModel

interface Repository {
    suspend fun retrieveProfile():Result<ProfileModel>
}