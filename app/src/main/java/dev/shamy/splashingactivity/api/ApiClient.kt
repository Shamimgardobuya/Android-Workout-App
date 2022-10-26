package dev.shamy.splashingactivity.api

import com.chuckerteam.chucker.api.ChuckerInterceptor
import dev.shamy.splashingactivity.WorkoutLog
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val client=OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor(WorkoutLog.appContext))
        .build()

    var retrofit =  Retrofit.Builder()
        .baseUrl("http://192.81.215.35")//cerate a domain  we want to connunicate
        .addConverterFactory(GsonConverterFactory.create())  //changes json ojects to creating kotlin bjects
        .build()
    fun<T>buildApiClient(apiInterface: Class<T>):T{

        return retrofit.create(apiInterface)
    }


}