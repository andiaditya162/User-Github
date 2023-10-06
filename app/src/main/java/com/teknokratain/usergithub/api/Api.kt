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
    @Headers("Authorization: token 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")
    fun getUsers(
        @Query("q") query: String
    ): Call<ResponseUser>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<ResponseUserDetail>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}