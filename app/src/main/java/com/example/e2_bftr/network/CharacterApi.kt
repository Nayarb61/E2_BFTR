package com.example.e2_bftr.network

import com.example.e2_bftr.model.chStaff
import com.example.e2_bftr.model.chStaffDetail
import com.example.e2_bftr.model.chStudentDetail
import com.example.e2_bftr.model.chStudents
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface CharacterApi {
    @GET
    fun getStudents(  // api/characters/students
        @Url url: String? = null
    ): Call<ArrayList<chStudents>>

    @GET
    fun getStaff( // api/characters/staff
        @Url url: String? = null
    ):Call<ArrayList<chStaff>>

    @GET("api/character/{id}")
    fun getStudentsDetail(
        @Path("id") id: String?
    ): Call<ArrayList<chStudentDetail>>

    @GET("api/character/{id}")
    fun getStaffDetail(
        @Path("id") id: String?
    ): Call<ArrayList<chStaffDetail>>

}