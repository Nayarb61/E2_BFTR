package com.example.e2_bftr.model

import com.google.gson.annotations.SerializedName

data class chStaff(
    @SerializedName("id")
    var id:String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("actor")
    var actor: String?,
    @SerializedName("house")
    var house: String?,
    @SerializedName("image")
    var icon: String?
)
