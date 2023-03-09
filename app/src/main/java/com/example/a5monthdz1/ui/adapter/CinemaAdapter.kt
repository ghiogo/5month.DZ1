package com.example.a5monthdz1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a5monthdz1.model.CinemaModel
import com.example.a5monthdz1.databinding.ItemMainBinding

class CinemaAdapter(
    val onItemClick: (modelCinema: CinemaModel) -> Unit
) : RecyclerView.Adapter<CinemaAdapter.MainViewHolder>() {

    private var list: List<CinemaModel> = ArrayList()

    fun setList(list: List<CinemaModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class MainViewHolder(private var binding: ItemMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onItemClick(list[adapterPosition])
            }
        }

        fun onBind(cinemaModel: CinemaModel) {
            binding.txtName.text = cinemaModel.name
            Glide.with(binding.image.context).load(cinemaModel.image).into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            ItemMainBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}