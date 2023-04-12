package com.ciurezu.gheorghe.dragos.clientmobile.data.model.save_quest

import com.ciurezu.gheorghe.dragos.clientmobile.data.model.User
import com.google.gson.annotations.SerializedName

data class SaveQuestRequest(
    @SerializedName("entrance_price")
    val entrancePrice:Int,
    @SerializedName("title")
    val title:String,
    @SerializedName("user")
    val user : User,
    @SerializedName("quest_category")
    val questCategory: QuestCategory,
    @SerializedName("prize")
    val prize: List<Prize>
)
