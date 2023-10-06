package com.teknokratain.usergithub.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.teknokratain.usergithub.api.RetrofitClient
import com.teknokratain.usergithub.data.local.FavoriteUser
import com.teknokratain.usergithub.data.local.FavoriteUserDao
import com.teknokratain.usergithub.data.local.UserDatabase
import com.teknokratain.usergithub.data.model.ResponseUserDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val user = MutableLiveData<ResponseUserDetail>()

    private var userDao: FavoriteUserDao?
    private var userDb: UserDatabase?

    init {
        userDb = UserDatabase.getDatabase(application)
        userDao = userDb?.favoriteUserDao()
    }

    fun setUserDetail(username: String){
        RetrofitClient.instanceApi
            .getUserDetail(username)
            .enqueue(object : Callback<ResponseUserDetail>{
                override fun onResponse(
                    call: Call<ResponseUserDetail>,
                    response: Response<ResponseUserDetail>
                ) {
                    if (response.isSuccessful){
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ResponseUserDetail>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
    }
    fun getDetail(): LiveData<ResponseUserDetail>{
        return user
    }
    fun addToFavorite(username: String, id: Int, avatar_url: String){
        CoroutineScope(Dispatchers.IO).launch {
            var user = FavoriteUser(
                username,
                id,
                avatar_url
            )
            userDao?.addToFavorite(user)
        }
    }
    suspend fun checkUser(id: Int) = userDao?.checkUser(id)

    fun removeFromFavorite(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.RemoveFromFavorite(id)
        }
    }
}