package com.capstone.healthhubnurse.ui.main

import com.capstone.healthhubnurse.models.Patient
import com.google.gson.annotations.SerializedName

data class PatientRequest(
    @SerializedName("username")
    var username: String,

    @SerializedName("status")
    var status: String,

    @SerializedName("success")
    var success: String = "",

    @SerializedName("patient_list")
    var patient_list: List<Patient>? = null
)