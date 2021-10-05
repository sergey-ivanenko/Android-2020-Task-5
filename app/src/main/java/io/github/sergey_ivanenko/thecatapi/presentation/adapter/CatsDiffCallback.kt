package io.github.sergey_ivanenko.thecatapi.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import io.github.sergey_ivanenko.thecatapi.domain.CatItem

class CatsDiffCallback(
    private val oldListCats: List<CatItem>,
    private val newListCats: List<CatItem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldListCats.size

    override fun getNewListSize(): Int = newListCats.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldListCats[oldItemPosition].id == newListCats[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldListCats[oldItemPosition] == newListCats[newItemPosition]
    }
}
