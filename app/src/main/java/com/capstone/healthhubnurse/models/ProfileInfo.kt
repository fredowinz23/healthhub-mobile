package com.capstone.healthhubnurse.models

import com.google.gson.annotations.SerializedName

data class ProfileInfo(
    @SerializedName("username")
    var username: String,

    @SerializedName("success")
    var success: String = "",

    @SerializedName("present")
    var present: String = "",

    @SerializedName("late")
    var late: String = "",

    @SerializedName("absent")
    var absent: String = "",

    @SerializedName("profile")
    var profile: User? = null
)