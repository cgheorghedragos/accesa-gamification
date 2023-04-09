package com.ciurezu.gheorghe.dragos.clientmobile.repository

import com.ciurezu.gheorghe.dragos.clientmobile.data.model.Resource
import com.ciurezu.gheorghe.dragos.clientmobile.data.model.login.LoginRequest
import com.ciurezu.gheorghe.dragos.clientmobile.network.GamificationApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GamificationRepositoryImpl @Inject constructor(
    private val gamificationApi: GamificationApi
) : GamificationRepository {
    override suspend fun loginUser(user: LoginRequest) = flow {
        emit(Resource.Loading(true))
        val response = gamificationApi.loginUser(user.username,user.password)
        emit(Resource.Success(response))
    }.catch { e ->
        emit(Resource.Error(e.toString()))
    }

}