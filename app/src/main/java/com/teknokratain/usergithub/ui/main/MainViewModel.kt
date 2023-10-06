package com.teknokratain.usergithub.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.teknokratain.usergithub.api.RetrofitClient
import com.teknokratain.usergithub.data.local.SettingPreferences
import com.teknokratain.usergithub.data.model.ResponseUser
import com.teknokratain.usergithub.data.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel (private val preferences: SettingPreferences): ViewModel() {
    val listUsers = MutableLiveData<ArrayList<User>>()
    fun getTheme() = preferences.getThemeSetting().asLiveData()
    fun setUsers(query: String){
        RetrofitClient.instanceApi
            .getUsers(query)
            .enqueue(object : Callback<ResponseUser>{
                override fun onResponse(
                    call: Call<ResponseUser>,
                    response: Response<ResponseUser>
                ) {
                    if (response.isSuccessful){
                        listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }
    fun getUsers(): LiveData<ArrayList<User>>{
        return listUsers
    }

    class Factory(private val preferences: SettingPreferences) :
            ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            MainViewModel(preferences) as T
    }
}