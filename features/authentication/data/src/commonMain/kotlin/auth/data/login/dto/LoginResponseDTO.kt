package auth.data.login.dto

import auth.data.PackageLevelAccess

@PackageLevelAccess
internal data  class LoginResponseDTO(val token:String)