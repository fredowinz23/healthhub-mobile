package com.capstone.healthhubnurse.models

import com.google.gson.annotations.SerializedName

data class FollowUp(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("medicalRecord")
    var medicalRecord: MedicalRecord? = null,

    @SerializedName("temperature")
    var temperature: String = "",

    @SerializedName("bloodPressure")
    var bloodPressure: String = "",

    @SerializedName("respiratoryRate")
    var respiratoryRate: String = "",

    @SerializedName("oxygen")
    var oxygen: String = "",

    @SerializedName("cardiacRate")
    var cardiacRate: String = "",

    @SerializedName("medications")
    var medications: String = "",

    @SerializedName("observations")
    var observations: String = "",

    @SerializedName("recommendations")
    var recommendations: String = "",

    @SerializedName("monitoredBy")
    var monitoredBy: User? = null,

    @SerializedName("dateAdded")
    var dateAdded: String = "",

)