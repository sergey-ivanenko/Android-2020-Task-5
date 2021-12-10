package io.github.sergey_ivanenko.thecatapi.presentation.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import io.github.sergey_ivanenko.thecatapi.R
import io.github.sergey_ivanenko.thecatapi.databinding.CatItemBinding
import io.github.sergey_ivanenko.thecatapi.domain.CatItem

class CatListPagingAdapter :
    PagingDataAdapter<CatItem, CatListPagingAdapter.CatListPagingViewHolder>(
        CatPagingDiffCallback()
    ) {

    override fun onBindViewHolder(holder: CatListPagingViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.onBind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatListPagingViewHolder {
        return CatListPagingViewHolder.create(parent)
    }

    class CatListPagingViewHolder(
        private val binding: CatItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        private var item: CatItem? = null
            private set

        fun onBind(item: CatItem) {
            this.item = item
            views {
                catImage.load(item.url) {
                    crossfade(true)
                    crossfade(1000)
                }

                catItem.setOnClickListener {
                    val args = Bundle()
                    args.putParcelable("currentCat", item)
                    /*args.putString("currentId", item.id)
                    args.putString("currentUrl", item.url)*/
                    it.findNavController()
                        .navigate(R.id.action_catListFragment_to_catDetailFragment, args)
                }
            }
            /*binding.catImage.load(item.url) {
                crossfade(true)
                crossfade(1000)
            }*/
        }

        private fun <T> views(block: CatItemBinding.() -> T): T = binding.block()

        companion object {
            fun create(parent: ViewGroup): CatListPagingViewHolder {
                return CatItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ).let(::CatListPagingViewHolder)
            }
        }
    }

    class CatPagingDiffCallback() : DiffUtil.ItemCallback<CatItem>() {
        override fun areItemsTheSame(oldItem: CatItem, newItem: CatItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CatItem, newItem: CatItem): Boolean {
            return oldItem == newItem
        }

    }
}
