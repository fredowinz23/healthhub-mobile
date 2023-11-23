package com.capstone.healthhubnurse.ui.main

import com.capstone.healthhubnurse.models.Symptom
import com.google.gson.annotations.SerializedName

data class MyScheduleListInfo(
    @SerializedName("username")
    var username: String,

    @SerializedName("success")
    var success: String = "",

    @SerializedName("schedule_list")
    var schedule_list: List<Symptom>? = null
)