package com.ciurezu.gheorghe.dragos.clientmobile.data.model

import com.google.gson.annotations.SerializedName

data class GamificationResponse<T>(
    @SerializedName("payload")
    val payload: T,
    @SerializedName("additional_message")
    val additionalMessage: String
)
