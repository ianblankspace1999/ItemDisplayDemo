package com.sample.bonial_examination.data.controller

import android.content.Context
import com.google.gson.GsonBuilder
import com.sample.bonial_examination.data.callback.ItemCallback
import com.sample.bonial_examination.data.client.ItemClient
import com.sample.bonial_examination.data.model.Embedded
import com.sample.bonial_examination.data.model.Item
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ItemController(context: Context, itemCallback: ItemCallback): Callback<Embedded> {

    private val callback = itemCallback
    private val baseUrl = "https://test-mobile-configuration-files.s3.eu-central-1.amazonaws.com"
    private lateinit var client: ItemClient

    fun start() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .client(OkHttpClient().newBuilder().addInterceptor(interceptor).build())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        client = retrofit.create(ItemClient::class.java)
        val call: Call<Embedded> = client.getItems()
        call.enqueue(this)
    }

    override fun onResponse(call: Call<Embedded>, response: Response<Embedded>) {
        if (response.isSuccessful) {
            response.body()?.let {
                it.embedded?.contents?.let { items ->
                    callback.onGetItemSuccess(items)
                }
            }
        } else {
            callback.onGetItemFailed()
        }
    }

    override fun onFailure(call: Call<Embedded>, t: Throwable) {
        callback.onGetItemFailed()
    }
}