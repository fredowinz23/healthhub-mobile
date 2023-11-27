package com.capstone.healthhubnurse.requests

import com.capstone.healthhubnurse.models.MedicalRecord
import com.capstone.healthhubnurse.models.Symptom
import com.capstone.healthhubnurse.models.User
import com.google.gson.annotations.SerializedName

data class MedicalRecordFormOptionRequest(
    @SerializedName("success")
    var success: String = "",

    @SerializedName("doctor_list")
    var doctor_list: List<User>? = null,

    @SerializedName("symptom_list")
    var symptom_list: List<Symptom>? = null
)