package com.sample.bonial_examination.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.bonial_examination.data.callback.ItemCallback
import com.sample.bonial_examination.data.controller.ItemController
import com.sample.bonial_examination.data.model.Embedded
import com.sample.bonial_examination.data.model.Item
import com.sample.bonial_examination.data.model.Resource
import com.sample.bonial_examination.data.model.ResourceState
import com.sample.bonial_examination.presentation.helper.ItemFilterHelper

class DisplayFragmentViewModel: ViewModel() {

    val itemsLiveData: MutableLiveData<Resource<ArrayList<Item>>> = MutableLiveData()

    fun getItems(context: Context) {
        itemsLiveData.postValue(
            Resource(status = ResourceState.LOADING)
        )
        ItemController(context, object: ItemCallback{
            override fun onGetItemSuccess(items: ArrayList<Item>) {
              itemsLiveData.postValue(
                  Resource(status = ResourceState.SUCCESS,
                      data = ItemFilterHelper().returnContentTypeFilterBrochure(items))
              )
            }

            override fun onGetItemFailed() {
              itemsLiveData.postValue(
                  Resource(status = ResourceState.ERROR))
            }

        }).start()
    }
}