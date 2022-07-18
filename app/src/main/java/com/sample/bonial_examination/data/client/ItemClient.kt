package com.sample.bonial_examination.data.client

import com.sample.bonial_examination.data.model.Embedded
import retrofit2.Call
import retrofit2.http.GET

interface ItemClient {

    @GET("stories-test/shelf.json")
    fun getItems(): Call<Embedded>
}