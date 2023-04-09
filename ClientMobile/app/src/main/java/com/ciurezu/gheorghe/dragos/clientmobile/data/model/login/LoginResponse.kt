package com.ciurezu.gheorghe.dragos.clientmobile.data.model.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    val accessToken: String
)
