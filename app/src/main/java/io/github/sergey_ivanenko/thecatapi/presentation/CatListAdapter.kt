package io.github.sergey_ivanenko.thecatapi.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.sergey_ivanenko.thecatapi.databinding.CatItemBinding

class CatListAdapter : RecyclerView.Adapter<CatListAdapter.CatItemViewHolder>() {

    class CatItemViewHolder(private val binding: CatItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatItemViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CatItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}
