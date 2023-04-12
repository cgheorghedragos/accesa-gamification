package com.ciurezu.gheorghe.dragos.clientmobile.data.model.ranking

import com.google.gson.annotations.SerializedName

data class RankingResponse(
    @SerializedName("username")
    val username : String,
    @SerializedName("nr_quest_solved")
    val nrQuestSolved : Int
)
