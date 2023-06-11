package com.example.e2_bftr.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e2_bftr.databinding.CharacterElementBinding
import com.example.e2_bftr.model.chStudents

class StudentsAdapter(private var context: Context, private var student: ArrayList<chStudents>, private var clickListener: (chStudents) -> Unit):RecyclerView.Adapter<StudentsAdapter.ViewHolderStd>(){

    class ViewHolderStd(view: CharacterElementBinding): RecyclerView.ViewHolder(view.root){
        val ivIcon = view.ivIcon
        val tvNombre = view.tvName
        val tvCasa = view.tvHouse
        val tvActor = view.tvActor
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderStd {
        val binding = CharacterElementBinding.inflate(LayoutInflater.from(context))
        return ViewHolderStd(binding)
    }

    override fun getItemCount(): Int = student.size

    override fun onBindViewHolder(holder: ViewHolderStd, position: Int) {
        holder.tvNombre.text = student[position].name
        holder.tvActor.text = student[position].actor
        holder.tvCasa.text = student[position].house
        Glide.with(context)
            .load(student[position].icon)
            .into(holder.ivIcon)

        //Para programar los eventos click a todos los elementos del ViewHolderStd
        // (tambien se podria elegir un elemento especifico cambiando itemView por el item deseado)
        holder.itemView.setOnClickListener{
            clickListener(student[position])
        }
    }

}

