package dev.shamy.splashingactivity.api

import dev.shamy.splashingactivity.models.LoginRequest
import dev.shamy.splashingactivity.models.LoginResponse
import dev.shamy.splashingactivity.models.RegisterResponse
import dev.shamy.splashingactivity.models.ReisterRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Body



interface ApiInterface {
    @POST("/register")
    suspend fun registerUser(@Body registerRequest:ReisterRequest):Response<RegisterResponse>
    @POST("/login")
    suspend  fun  login(@Body loginRequest:LoginRequest):Response<LoginResponse>


}