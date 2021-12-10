package io.github.sergey_ivanenko.thecatapi.domain

import android.os.Parcelable
import io.github.sergey_ivanenko.thecatapi.data.remote.cat.CatApiDataItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatItem(val id: String, val url: String) : Parcelable {
    object CatMapper {
        fun from(catApiDataItem: CatApiDataItem) = CatItem(catApiDataItem.id, catApiDataItem.url)
    }
}
