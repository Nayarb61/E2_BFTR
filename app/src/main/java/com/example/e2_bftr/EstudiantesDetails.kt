package com.example.e2_bftr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.e2_bftr.databinding.ActivityEstudiantesDetailsBinding
import com.example.e2_bftr.model.chStudentDetail
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

        call.enqueue(object : Callback<ArrayList<chStudentDetail>> {
            override fun onResponse(
                call: Call<ArrayList<chStudentDetail>>,
                response: Response<ArrayList<chStudentDetail>>
            ) {
                binding.pbConexionStdD.visibility = View.GONE
             //   Toast.makeText(this@EstudiantesDetails,"CONECTADO", Toast.LENGTH_SHORT).show()

                val student = response.body()
             //   Log.d(Constants.LOGTAG, "Datos: ${response.body().toString()}")

                if (response.isSuccessful) {
                    val studentD = student?.getOrNull(0)

                    binding.tvName.text = studentD?.name
                    binding.tvPatronus.text = studentD?.patronus
                    binding.tvSangre.text = studentD?.ancestry
                    binding.tvGenero.text = studentD?.genero
                    binding.tvCumpleaOs.text = studentD?.cumple

                    Glide.with(this@EstudiantesDetails)
                        .load(studentD?.icon)
                        .into(binding.ivIcon)
                }
            }

            override fun onFailure(call: Call<ArrayList<chStudentDetail>>, t: Throwable) {
                Toast.makeText(this@EstudiantesDetails,getString(R.string.NoConexion), Toast.LENGTH_SHORT).show()
            }
        })
    }
}