package com.ciurezu.gheorghe.dragos.clientmobile.data.model.save_quest

import com.google.gson.annotations.SerializedName

data class Prize(
    @SerializedName("tokens")
    val tokens: Int,
    @SerializedName("badge_for_reward")
    val badgeForReward : List<Badge>
)
