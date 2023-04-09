package com.ciurezu.gheorghe.dragos.clientmobile.network

import com.ciurezu.gheorghe.dragos.clientmobile.data.model.GamificationResponse
import com.ciurezu.gheorghe.dragos.clientmobile.data.model.User
import com.ciurezu.gheorghe.dragos.clientmobile.data.model.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface GamificationApi {
    @GET("login")
    suspend fun loginUser(
        @Query("username") username: String,
        @Query("password") password: String
    ): LoginResponse

    @GET("v1/bird/get-user-bird")
    suspend fun getUserBird(@Query("username") username: String): GamificationResponse<String>

    @GET("v1/bird/get-another-bird")
    suspend fun getAnotherBird(@Query("username") username: String): GamificationResponse<String>

    @PUT("v1/bird/bird-loved-update-user")
    suspend fun birdLoved(@Body user: User): GamificationResponse<String>

    @GET("v1/quest/get-all-participants-for-quest")
    suspend fun getAllParticipantsForQuest()

    @GET("v1/quest/get-all-prizes")
    suspend fun getAllPrizes()

    @GET("api/v1/quest/get-all-active-quests")
    suspend fun getAllActiveQuests()

}