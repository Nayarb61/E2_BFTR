package com.example.e2_bftr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e2_bftr.adapters.StudentsAdapter
import com.example.e2_bftr.databinding.ActivityEstudiantesListBinding
import com.example.e2_bftr.model.chStudents
import com.example.e2_bftr.network.CharacterApi
import com.example.e2_bftr.network.RetrofitService
import com.example.e2_bftr.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EstudiantesList : AppCompatActivity() {

    private lateinit var binding: ActivityEstudiantesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEstudiantesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val call = RetrofitService.getRetrofit().create(CharacterApi::class.java)
            .getStudents("api/characters/students")

        call.enqueue(object : Callback<ArrayList<chStudents>>{
            override fun onResponse(
                call: Call<ArrayList<chStudents>>,
                response: Response<ArrayList<chStudents>>
            ) {
                if(response.isSuccessful){
                    binding.pbConexionStd.visibility = View.GONE

                    Toast.makeText(this@EstudiantesList,"CONECTADO",Toast.LENGTH_SHORT).show()
                    Log.d(Constants.LOGTAG, "SERVER: ${response.toString()}")

                    Log.d(Constants.LOGTAG, "Datos: ${response.body().toString()}")

                    binding.rvListaStudent.layoutManager = LinearLayoutManager(this@EstudiantesList)

                    binding.rvListaStudent.adapter = StudentsAdapter(this@EstudiantesList, response.body()!!)
                }
            }

            override fun onFailure(call: Call<ArrayList<chStudents>>, t: Throwable) {
                Toast.makeText(this@EstudiantesList," NO CONECTADO",Toast.LENGTH_SHORT).show()
            }

        })
    }
}