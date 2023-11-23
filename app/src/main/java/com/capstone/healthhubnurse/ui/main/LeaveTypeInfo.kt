package com.capstone.healthhubnurse.ui.main

import com.capstone.healthhubnurse.models.LeaveType
import com.google.gson.annotations.SerializedName

data class LeaveTypeInfo(
    @SerializedName("username")
    var username: String,

    @SerializedName("success")
    var success: String = "",

    @SerializedName("leave_list")
    var leave_list: List<LeaveType>? = null
)