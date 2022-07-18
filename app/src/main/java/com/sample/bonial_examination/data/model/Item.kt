package com.sample.bonial_examination.data.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("placement")
    val placement: String? = null,
    @SerializedName("adFormat")
    val adFormat: String? = null,
    @SerializedName("contentFormatSource")
    val contentFormatSource: String? = null,
    @SerializedName("contentType")
    val contentType: String? = null,
    @SerializedName("content")
    val content: Any? = null) {

    data class Content(
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("title")
        val title: String? = null,
        @SerializedName("validFrom")
        val validFrom: String? = null,
        @SerializedName("validUntil")
        val validUntil: String? = null,
        @SerializedName("publishedFrom")
        val publishedFrom: String? = null,
        @SerializedName("publishedUntil")
        val publishedUntil: String? = null,
        @SerializedName("type")
        val type: String? = null,
        @SerializedName("pageCount")
        val pageCount: Int? = null,
        @SerializedName("publisher")
        val publisher: Publisher? = null,
        @SerializedName("retailer")
        val retailer: Retailer? = null,
        @SerializedName("brochureImage")
        val brochureImage: String? = null,
        @SerializedName("isEcommerce")
        val isEcommerce: Boolean? = false,
        @SerializedName("distance")
        val distance: Double? = null,
        @SerializedName("hideValidityData")
        val hideValidityData: Boolean? = false
    ) {
        data class Publisher(
            @SerializedName("id")
            val id: Int? = null,
            @SerializedName("name")
            val name: String? = null,
            @SerializedName("type")
            val type: String? = null
        )

        data class Retailer(
            @SerializedName("id")
            val id: Int? = null,
            @SerializedName("name")
            val name: String? = null
        )
    }
}