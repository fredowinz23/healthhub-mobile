package com.capstone.healthhubnurse.requests

import com.capstone.healthhubnurse.models.FollowUp
import com.capstone.healthhubnurse.models.MedicalRecord
import com.google.gson.annotations.SerializedName

data class MonitoringListRequest(
    @SerializedName("mrId")
    var mrId: Int,

    @SerializedName("success")
    var success: String = "",

    @SerializedName("monitoring_list")
    var monitoring_list: List<FollowUp>? = null
)