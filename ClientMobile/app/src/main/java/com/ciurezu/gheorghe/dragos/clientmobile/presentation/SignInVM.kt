package com.ciurezu.gheorghe.dragos.clientmobile.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciurezu.gheorghe.dragos.clientmobile.data.model.Resource
import com.ciurezu.gheorghe.dragos.clientmobile.data.model.login.LoginRequest
import com.ciurezu.gheorghe.dragos.clientmobile.data.model.login.LoginResponse
import com.ciurezu.gheorghe.dragos.clientmobile.repository.GamificationRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInVM @Inject constructor(
    private val gamificationRepository: GamificationRepository
) : ViewModel() {
    val signInResponse : MutableLiveData<Resource<LoginResponse>> = MutableLiveData()

    fun signInUser(username : String, password : String){
        val user = LoginRequest(username,password)
        viewModelScope.launch {
            gamificationRepository.loginUser(user).collect {
                signInResponse.postValue(it)
            }

        }
    }
}