package com.example.e2_bftr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e2_bftr.adapters.StaffAdapter
import com.example.e2_bftr.databinding.ActivityPersonalListBinding
import com.example.e2_bftr.model.chStaff
import com.example.e2_bftr.network.CharacterApi
import com.example.e2_bftr.network.RetrofitService
import com.example.e2_bftr.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonalList : AppCompatActivity() {

    private lateinit var binding: ActivityPersonalListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val call = RetrofitService.getRetrofit().create(CharacterApi::class.java)
            .getStaff("api/characters/staff")

        call.enqueue(object: Callback<ArrayList<chStaff>>{
            override fun onResponse(
                call: Call<ArrayList<chStaff>>,
                response: Response<ArrayList<chStaff>>
            ) {
                binding.pbConexionStf.visibility = View.GONE
            //    Toast.makeText(this@PersonalList,"CONECTADO", Toast.LENGTH_SHORT).show()

            //    Log.d(Constants.LOGTAG, "SERVER: ${response.toString()}")

             //   Log.d(Constants.LOGTAG, "Datos: ${response.body().toString()}")

                binding.rvListaStaff.layoutManager = LinearLayoutManager(this@PersonalList)

                binding.rvListaStaff.adapter = StaffAdapter(this@PersonalList, response.body()!!,
                    {selectStaff: chStaff -> characterClicked(selectStaff)})


            }

            override fun onFailure(call: Call<ArrayList<chStaff>>, t: Throwable) {
                Toast.makeText(this@PersonalList,getString(R.string.NoConexion),Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun characterClicked(chStaff: chStaff){
      //  Toast.makeText(this,"Click en ${chStaff.name}",Toast.LENGTH_SHORT).show()

        val bundle = Bundle()
        bundle.putString("id",chStaff.id)

        val intent = Intent(this, StaffDetails::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}