package com.sample.bonial_examination.presentation.helper

import com.google.gson.Gson
import com.sample.bonial_examination.data.model.Item

open class ItemFilterHelper {

    fun returnContentTypeFilterBrochure(items: ArrayList<Item>): ArrayList<Item> {
        var filterredItem: MutableList<Item> = ArrayList()
        for (item in items) {
            if (item.contentType == "brochure" || item.contentType == "brochurePremium") {
                filterredItem.add(item)
            }
        }
        return filterredItem as ArrayList<Item>
    }

    fun returnLessEqualFiveKmDistance(items: ArrayList<Item>): ArrayList<Item> {
        var filterredItem: MutableList<Item> = ArrayList()
        for (item in items) {
            val content = Gson().fromJson(Gson().toJson(item.content), Item.Content::class.java)
            content.distance?.let {
                if (content.distance < 5) {
                    filterredItem.add(item)
                }
            }

        }
        return filterredItem as ArrayList<Item>
    }
}