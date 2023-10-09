package com.teknokratain.usergithub.api

import com.teknokratain.usergithub.data.model.ResponseUser
import com.teknokratain.usergithub.data.model.ResponseUserDetail
import com.teknokratain.usergithub.data.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token {your token here}")
    fun getUsers(
        @Query("q") query: String
    ): Call<ResponseUser>

    @GET("users/{username}")
    @Headers("Authorization: token  {your token here}")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<ResponseUserDetail>

    @GET("users/{username}/followers")
    @Headers("Authorization: token  {your token here}")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token  {your token here}")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}
