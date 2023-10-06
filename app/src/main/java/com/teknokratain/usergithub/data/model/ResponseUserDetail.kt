package com.teknokratain.usergithub.data.model

data class ResponseUserDetail(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val followers_url: String,
    val following_url: String,
    val name: String,
    val following: Int,
    var followers: Int
)
