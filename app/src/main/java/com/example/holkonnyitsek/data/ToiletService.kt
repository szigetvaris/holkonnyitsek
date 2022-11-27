package com.example.holkonnyitsek.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ToiletService {
    @POST("/wc/add")
    suspend fun addToilet(@Body wcData: WCObject) : Response<String>

    @GET("/wc/get/all")
    suspend fun getToilets() : Response<MutableList<WCObject>>

    //@DELETE("/wc/del/:id")
    @GET("/wc/del/{id}")
    suspend fun deleteToilet(@Path("id") id: String) : Response<String>
}

object RetrofitHelper {

    val baseUrl = "http://10.0.2.2:3000/"

    fun getInstance(): Retrofit {
        var gson: Gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}