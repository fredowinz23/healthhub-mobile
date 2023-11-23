package com.capstone.healthhubnurse.models

import com.google.gson.annotations.SerializedName

data class LeaveType(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("name")
    var name: String = "",

    @SerializedName("description")
    var description: String = ""
)