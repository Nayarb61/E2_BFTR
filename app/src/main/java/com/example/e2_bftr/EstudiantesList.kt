package com.example.e2_bftr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e2_bftr.adapters.StudentsAdapter
import com.example.e2_bftr.databinding.ActivityEstudiantesListBinding
import com.example.e2_bftr.model.chStudents
import com.example.e2_bftr.network.CharacterApi
import com.example.e2_bftr.network.RetrofitService
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

                  //  Toast.makeText(this@EstudiantesList,"CONECTADO",Toast.LENGTH_SHORT).show()
                  //  Log.d(Constants.LOGTAG, "SERVER: ${response.toString()}")

                  //  Log.d(Constants.LOGTAG, "Datos: ${response.body().toString()}")

                    binding.rvListaStudent.layoutManager = LinearLayoutManager(this@EstudiantesList)

                    binding.rvListaStudent.adapter = StudentsAdapter(this@EstudiantesList, response.body()!!
                        ,{selectStudent: chStudents -> characterClicked(selectStudent)})
                }
            }

            override fun onFailure(call: Call<ArrayList<chStudents>>, t: Throwable) {
                Toast.makeText(this@EstudiantesList,getString(R.string.NoConexion),Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun characterClicked(chstudents: chStudents){
      //  Toast.makeText(this,"Click en ${chstudents.name}",Toast.LENGTH_SHORT).show()

        val bundle = Bundle()
        bundle.putString("id",chstudents.id)

        val intent = Intent(this,EstudiantesDetails::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}