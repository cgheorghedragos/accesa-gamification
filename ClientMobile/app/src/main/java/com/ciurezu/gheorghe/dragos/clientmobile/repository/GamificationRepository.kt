package com.ciurezu.gheorghe.dragos.clientmobile.repository

import com.ciurezu.gheorghe.dragos.clientmobile.data.model.Resource
import com.ciurezu.gheorghe.dragos.clientmobile.data.model.login.LoginRequest
import com.ciurezu.gheorghe.dragos.clientmobile.data.model.login.LoginResponse
import com.ciurezu.gheorghe.dragos.clientmobile.network.GamificationApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GamificationRepository {
    suspend fun loginUser(user : LoginRequest) : Flow<Resource<LoginResponse>>

}