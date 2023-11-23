package com.capstone.healthhubnurse.auth

import com.google.gson.annotations.SerializedName
import com.capstone.healthhubnurse.models.User

data class LoginInfo(
    @SerializedName("username")
    var username: String,

    @SerializedName("password")
    var password: String,

    @SerializedName("success")
    var success: String = "",

    @SerializedName("profile")
    var profile: User? = null
)