package com.ciurezu.gheorghe.dragos.clientmobile.data.model.quest.save_quest

import com.ciurezu.gheorghe.dragos.clientmobile.data.model.badge.BadgeResponse
import com.google.gson.annotations.SerializedName

data class Badge(
    @SerializedName("badge")
    val badge: BadgeResponse
)
