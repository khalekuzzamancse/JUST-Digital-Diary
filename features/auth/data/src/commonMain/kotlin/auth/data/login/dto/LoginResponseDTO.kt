package auth.data.login.dto

import auth.data.PackageLevelAccess

@PackageLevelAccess
data  class LoginResponseDTO(val token:String)