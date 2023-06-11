package com.example.e2_bftr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.e2_bftr.databinding.ActivityEstudiantesDetailsBinding
import com.example.e2_bftr.model.chStudentDetail
import com.example.e2_bftr.model.chStudents
import com.example.e2_bftr.network.CharacterApi
import com.example.e2_bftr.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EstudiantesDetails : AppCompatActivity() {

    private lateinit var binding: ActivityEstudiantesDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEstudiantesDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val id = bundle?.getString("id", "")

        val call = RetrofitService.getRetrofit().create(CharacterApi::class.java).getStudentsDetail(id)

        call.enqueue(object : Callback<chStudentDetail> {
            override fun onResponse(call: Call<chStudentDetail>, response: Response<chStudentDetail>
            ) {
                binding.pbConexionStdD.visibility = View.GONE
                Toast.makeText(this@EstudiantesDetails, "CONECTADO", Toast.LENGTH_SHORT).show()
                binding.tvName.text = response.body()!!.name
                binding.tvGenero.text = response.body()!!.genero
                binding.tvCumpleaOs.text = response.body()!!.cumple
                binding.tvMago.text = response.body()!!.mago.toString()
                binding.tvVivo.text = response.body()!!.vivo.toString()
                binding.tvSangre.text = response.body()!!.ancestry
                binding.tvPatronus.text = response.body()!!.patronus

                Glide.with(this@EstudiantesDetails)
                    .load(response.body()!!.icon)
                    .into(binding.ivIcon)
            }

            override fun onFailure(call: Call<chStudentDetail>, t: Throwable) {
                Toast.makeText(this@EstudiantesDetails, "No hay conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }
}