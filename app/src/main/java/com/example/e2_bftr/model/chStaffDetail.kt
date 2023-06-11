package com.example.e2_bftr.model

import com.google.gson.annotations.SerializedName

data class chStaffDetail(
    @SerializedName("id")
    var id:String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("image")
    var icon: String?,
    @SerializedName("gender")
    var genero: String?,
    @SerializedName("dateOfBirth")
    var cumple: String?,
    @SerializedName("ancestry")
    var ancestry: String?,
    @SerializedName("patronus")
    var patronus: String?
)

