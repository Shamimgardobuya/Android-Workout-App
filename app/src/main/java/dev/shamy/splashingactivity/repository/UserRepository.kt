package dev.shamy.splashingactivity.repository

import dev.shamy.splashingactivity.api.ApiClient
import dev.shamy.splashingactivity.api.ApiInterface
import dev.shamy.splashingactivity.models.LoginRequest
import dev.shamy.splashingactivity.models.ReisterRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {
    //CREATE instance of apiclient
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun login(loginRequest: LoginRequest) = withContext(Dispatchers.IO) {
        val response = apiClient.login(loginRequest)   //invoking function
        return@withContext response
    }
    suspend fun registerUser(registerRequest: ReisterRequest) = withContext(Dispatchers.IO) {
        val response = apiClient.registerUser(registerRequest)   //invoking function of register
        return@withContext response



    }
//class RegisterRepository{
//    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
//    suspend fun registerUser(registerRequest: ReisterRequest) = withContext(Dispatchers.IO) {
//        val response = apiClient.registerUser(registerRequest)   //invoking function of register
//        return@withContext response


    }
//}