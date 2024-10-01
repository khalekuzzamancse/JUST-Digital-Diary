package profile.presentationlogic.model

import domain.model.ProfileModel
typealias UiProfileModel=profile.presentationlogic.model.ProfileModel
object ModelMapper {
    fun toUiModel(model: ProfileModel)=UiProfileModel(
        name = model.name,
        username = model.username,
        email = model.email
    )
}