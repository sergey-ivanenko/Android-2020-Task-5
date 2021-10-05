package io.github.sergey_ivanenko.thecatapi.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import io.github.sergey_ivanenko.thecatapi.databinding.CatItemBinding
import io.github.sergey_ivanenko.thecatapi.domain.CatItem

class CatListAdapter : RecyclerView.Adapter<CatListAdapter.CatViewHolder>() {

    var catList = emptyList<CatItem>()
        set(newValue) {
            val diffCallback = CatsDiffCallback(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    class CatViewHolder(private val binding: CatItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var item: CatItem? = null
            private set

        fun onBind(item: CatItem) {
            this.item = item
            /*binding.catImage.load(item.url) {
                crossfade(true)
                crossfade(1000)
            }*/
            views {
                catImage.load(item.url)
            }
        }

        private fun <T> views(block: CatItemBinding.() -> T): T = binding.block()

        companion object {
            fun create(parent: ViewGroup) = CatItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).let(::CatViewHolder)
            /*fun create(parent: ViewGroup): CatViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = CatItemBinding.inflate(inflater, parent, false)

                return CatViewHolder(binding)
            }*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val catItem = catList[position]
        holder.onBind(catItem)
    }

    override fun getItemCount(): Int = catList.size
}
