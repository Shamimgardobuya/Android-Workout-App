package dev.shamy.splashingactivity.api

import dev.shamy.splashingactivity.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header


interface ApiInterface {
    @POST("/register")
    suspend fun registerUser(@Body registerRequest:ReisterRequest):Response<RegisterResponse>
    @POST("/login")
    suspend  fun  login(@Body loginRequest:LoginRequest):Response<LoginResponse>
    @GET("exercise-cateories")
    suspend fun fetchExerciseCategories(@Header("Authorization")accessToken:String):Response<List<ExerciseCategory>>

}