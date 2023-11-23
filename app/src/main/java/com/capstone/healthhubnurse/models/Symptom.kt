package com.capstone.healthhubnurse.models

import com.google.gson.annotations.SerializedName

data class Symptom(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("name")
    var name: String = "",

)