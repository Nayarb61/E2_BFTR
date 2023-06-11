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

        val id = bundle?.getString("id", "")
        val call = RetrofitService.getRetrofit().create(CharacterApi::class.java).getStaffDetail(id)

        call.enqueue(object : Callback<ArrayList<chStaffDetail>> {
            override fun onResponse(call: Call<ArrayList<chStaffDetail>>, response: Response<ArrayList<chStaffDetail>>
            ) {
                binding.pbConexionStdD.visibility = View.GONE
                //   Toast.makeText(this@EstudiantesDetails,"CONECTADO", Toast.LENGTH_SHORT).show()

                val staf = response.body()
                //   Log.d(Constants.LOGTAG, "Datos: ${response.body().toString()}")

                if (response.isSuccessful) {
                    val stafD = staf?.getOrNull(0)

                    binding.tvName.text = stafD?.name
                    binding.tvPatronus.text = getString(R.string.patronus,stafD?.patronus)
                    binding.tvSangre.text = getString(R.string.ancestry,stafD?.ancestry)
                    binding.tvGenero.text = getString(R.string.genero,stafD?.genero)
                    binding.tvCumpleaOs.text = getString(R.string.cumple,stafD?.cumple)
                    binding.tvCasa.text = getString(R.string.casa,stafD?.house)
                    if(stafD?.mago == true){
                        binding.tvWizard.text = getString(R.string.Esmago)

                    }else{
                        binding.tvWizard.text = getString(R.string.NoEsmago)
                    }
                    if (stafD?.vivo == true ){
                        binding.tvVivo.text = getString(R.string.Esvivo)
                    }else{
                        binding.tvVivo.text = getString(R.string.NoESvivo)
                    }
                    Glide.with(this@StaffDetails)
                        .load(stafD?.icon)
                        .into(binding.ivIcon)
                }
            }

            override fun onFailure(call: Call<ArrayList<chStaffDetail>>, t: Throwable) {
                Toast.makeText(this@StaffDetails,getString(R.string.NoConexion), Toast.LENGTH_SHORT).show()
            }
        })
    }
}