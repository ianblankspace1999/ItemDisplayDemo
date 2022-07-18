package com.sample.bonial_examination.data.model

import com.google.gson.annotations.SerializedName

data class Embedded(
    @SerializedName("_embedded")
    val embedded: Contents? = null
) {
    data class Contents(
        @SerializedName("contents")
        val contents: ArrayList<Item>? = null
    )
}
