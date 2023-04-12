package com.ciurezu.gheorghe.dragos.clientmobile.presentation.app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciurezu.gheorghe.dragos.clientmobile.data.SharedPrefs
import com.ciurezu.gheorghe.dragos.clientmobile.data.model.GamificationResponse
import com.ciurezu.gheorghe.dragos.clientmobile.data.model.Resource
import com.ciurezu.gheorghe.dragos.clientmobile.data.model.User
import com.ciurezu.gheorghe.dragos.clientmobile.data.model.bird.BirdResponse
import com.ciurezu.gheorghe.dragos.clientmobile.data.model.login.LoginRequest
import com.ciurezu.gheorghe.dragos.clientmobile.data.model.login.LoginResponse
import com.ciurezu.gheorghe.dragos.clientmobile.repository.GamificationRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeVM  @Inject constructor(
    private val gamificationRepository: GamificationRepository,
    private val sharedPrefs: SharedPrefs
) : ViewModel() {

    val birdResponse : MutableLiveData<Resource<GamificationResponse<String>>> = MutableLiveData()

    fun getProgress() : Int{
        return sharedPrefs.getBirdLove()
    }

    fun getAnotherBird() {
        val user = User(sharedPrefs.getUsername().toString(),"")
        viewModelScope.launch {
            gamificationRepository.getAnotherBird(user).collect {
                birdResponse.postValue(it)
            }

        }
    }

    fun getBirdForUser() {
        val user = User(sharedPrefs.getUsername().toString(),"")
        viewModelScope.launch {
            gamificationRepository.getAnotherBird(user).collect {
                birdResponse.postValue(it)
            }

        }
    }

}