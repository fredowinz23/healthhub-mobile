package com.capstone.healthhubnurse.requests

import com.google.gson.annotations.SerializedName

data class NewMedicalRecordRequest(
    @SerializedName("patientId")
    var patientId: Int,

    @SerializedName("doctorId")
    var doctorId: Int,

    @SerializedName("reasonForAdmission")
    var reasonForAdmission: String,

    @SerializedName("allergies")
    var allergies: String,

    @SerializedName("medications")
    var medications: String,

    @SerializedName("bloodType")
    var bloodType: String,

    @SerializedName("symptomId")
    var symptomId: Int,

    @SerializedName("doctorsOrders")
    var doctorsOrders: String,

    @SerializedName("success")
    var success: Boolean = false,
)