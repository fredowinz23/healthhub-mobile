package com.capstone.healthhubnurse.models

import com.google.gson.annotations.SerializedName

data class MedicalRecord(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("patient")
    var patient: Patient? = null,

    @SerializedName("doctor")
    var doctor: User? = null,

    @SerializedName("reasonForAdmission")
    var reasonForAdmission: String = "",

    @SerializedName("allergies")
    var allergies: String = "",

    @SerializedName("medications")
    var medications: String = "",

    @SerializedName("bloodType")
    var bloodType: String = "",

    @SerializedName("symptom")
    var symptom: Symptom? = null,

    @SerializedName("doctorsOrders")
    var doctorsOrders: String = "",

    @SerializedName("dateAdded")
    var dateAdded: String = "",

    @SerializedName("status")
    var status: String = "",

)