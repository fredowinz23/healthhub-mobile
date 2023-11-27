package com.capstone.healthhubnurse.models

import com.google.gson.annotations.SerializedName

data class Patient(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("fullName")
    var fullName: String = "",

    @SerializedName("dob")
    var dob: String = "",

    @SerializedName("gender")
    var gender: String = "",

    @SerializedName("address")
    var address: String = "",

    @SerializedName("city")
    var city: String = "",

    @SerializedName("phoneNumber")
    var phoneNumber: String = "",

    @SerializedName("email")
    var email: String = "",

    @SerializedName("emergencyContactName")
    var emergencyContactName: String = "",

    @SerializedName("emergencyPhoneNumber")
    var emergencyPhoneNumber: String = "",

    @SerializedName("relationship")
    var relationship: String = "",
)