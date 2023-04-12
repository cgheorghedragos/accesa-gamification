package com.ciurezu.gheorghe.dragos.clientmobile.data.model.save_quest

import com.google.gson.annotations.SerializedName

data class QuestCategory(
    @SerializedName("category_name")
    val questCategory: String
)