package com.example.e2_bftr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.e2_bftr.databinding.ActivityStaffDetailsBinding
import com.example.e2_bftr.model.chStaffDetail
import com.example.e2_bftr.network.CharacterApi
import com.example.e2_bftr.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StaffDetails : AppCompatActivity() {

    private lateinit var binding: ActivityStaffDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStaffDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        val id_Stf = bundle?.getString("id","")

        val call = RetrofitService.getRetrofit().create(CharacterApi::class.java).getStaffDetail(id_Stf)

        call.enqueue(object: Callback<chStaffDetail>{
            override fun onResponse(call: Call<chStaffDetail>, response: Response<chStaffDetail>
            ) {
                binding.pbConexionStdD.visibility = View.GONE
                Toast.makeText(this@StaffDetails,"CONECTADO", Toast.LENGTH_SHORT).show()

                binding.tvName.text = response.body()!!.name
                binding.tvGenero.text = response.body()!!.genero
                binding.tvCumpleaOs.text = response.body()!!.cumple
                binding.tvMago.text = response.body()!!.mago.toString()
                binding.tvVivo.text = response.body()!!.vivo.toString()
                binding.tvSangre.text = response.body()!!.ancestry
                binding.tvPatronus.text = response.body()!!.patronus

                Glide.with(this@StaffDetails)
                    .load(response.body()!!.icon)
                    .into(binding.ivIcon)

            }
            override fun onFailure(call: Call<chStaffDetail>, t: Throwable) {
                Toast.makeText(this@StaffDetails,"No hay conexión",Toast.LENGTH_SHORT).show()
            }
        })
    }
}