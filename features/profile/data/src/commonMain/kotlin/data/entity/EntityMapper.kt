package data.entity

import domain.model.ProfileModel

object EntityMapper {
    fun toModel(entity: ProfileEntity)=ProfileModel(
        name = entity.name,
        username = entity.username,
        email = entity.email
    )
}