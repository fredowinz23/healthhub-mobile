package com.capstone.healthhubnurse.ui.main

import com.capstone.healthhubnurse.models.MedicalRecord
import com.google.gson.annotations.SerializedName

data class MedicalRecordRequest(
    @SerializedName("patientId")
    var patientId: Int,

    @SerializedName("success")
    var success: String = "",

    @SerializedName("md_list")
    var md_list: List<MedicalRecord>? = null
)