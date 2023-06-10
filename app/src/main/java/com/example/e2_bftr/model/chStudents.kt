package com.example.e2_bftr.model

import com.google.gson.annotations.SerializedName

data class chStudents(
    @SerializedName("id")
    var id:String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("actor")
    var actor: String?,
    @SerializedName("house")
    var house: String?,
    @SerializedName("image")
    var icon: String?,
   @SerializedName("gender")
    var genero: String?,
    @SerializedName("dateOfBirth")
    var cumple: String?,
    @SerializedName("wizard")
    var wizard: Boolean?,
    @SerializedName("ancestry")
    var ancestry: String?,
    @SerializedName("patronus")
    var patronus: String?,
    @SerializedName("alive")
    var alive: Boolean?
)
