package com.sample.bonial_examination.data.callback

import com.sample.bonial_examination.data.model.Embedded
import com.sample.bonial_examination.data.model.Item

interface ItemCallback {
    fun onGetItemSuccess(items: ArrayList<Item>)
    fun onGetItemFailed()
}