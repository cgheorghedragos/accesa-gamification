package com.ciurezu.gheorghe.dragos.clientmobile.data

import android.content.Context
import android.content.SharedPreferences
import android.location.Location
import javax.inject.Inject

class SharedPrefs  @Inject constructor(context: Context){
    companion object {
        const val PREFS_NAME = "app_preferences"
        const val KEY_USERNAME = "no username"
    }

    private val sharedPrefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveUserLocation(username : String) =
        with(sharedPrefs.edit()) {
            putString(KEY_USERNAME, username)
            apply()
        }

    fun getUsername() : String?{
         val username = sharedPrefs.getString(KEY_USERNAME,"no username")
        return username
    }
}