package com.ciurezu.gheorghe.dragos.clientmobile.presentation.app.winner

import com.ciurezu.gheorghe.dragos.clientmobile.data.model.participant.ParticipantResponse
import com.ciurezu.gheorghe.dragos.clientmobile.data.model.quest.get_all_active_quests.QuestResponse
import com.google.gson.annotations.SerializedName

data class MarkWinnerRequest(
    @SerializedName("quest")
    val questDto: QuestResponse,
    @SerializedName("participants")
    val participants: List<ParticipantResponse>
)