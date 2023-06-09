package com.example.e2_bftr

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.e2_bftr.databinding.ActivityMainBinding
import com.example.e2_bftr.databinding.ActivityMenuBinding

class Menu : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEstudiante.setOnClickListener{
            val intent = Intent(this,EstudiantesList::class.java)
            startActivity(intent)
        }

        binding.btnStaff.setOnClickListener{
            val intent = Intent(this,PersonalList::class.java)
            startActivity(intent)
        }
    }
}