package com.example.e2_bftr.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e2_bftr.databinding.StfCharacterElementBinding
import com.example.e2_bftr.model.chStaff

class StaffAdapter(private var context: Context, private var staf: ArrayList<chStaff>, private var clickListener: (chStaff) -> Unit): RecyclerView.Adapter<StaffAdapter.StfViewHolder>() {

    class StfViewHolder(view: StfCharacterElementBinding):RecyclerView.ViewHolder(view.root) {
        val ivIcon = view.ivIcon
        val tvNombre = view.tvName
        val tvActor = view.tvActor
        val tvCasa = view.tvHouse
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StfViewHolder {
        val binding = StfCharacterElementBinding.inflate(LayoutInflater.from(context))
        return StfViewHolder(binding)
    }

    override fun getItemCount(): Int = staf.size

    override fun onBindViewHolder(holder: StfViewHolder, position: Int) {
        holder.tvNombre.text = staf[position].name
        holder.tvActor.text = staf[position].actor
        holder.tvCasa.text = staf[position].house

        Glide.with(context)
            .load(staf[position].icon)
            .into(holder.ivIcon)

        holder.itemView.setOnClickListener {
            clickListener(staf[position])
        }
    }
}