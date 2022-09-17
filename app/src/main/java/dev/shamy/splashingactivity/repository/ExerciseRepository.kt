package dev.shamy.splashingactivity.repository

import dev.shamy.splashingactivity.api.ApiClient
import dev.shamy.splashingactivity.api.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExerciseRepository {
    val apiClient=ApiClient.buildApiClient(ApiInterface::class.java)   //instantiate api client
    suspend fun  fetchExercisesCategories(accessToken:String)=
        withContext(Dispatchers.IO){   //switches courotine to another thread
            return@withContext apiClient.fetchExerciseCategories(accessToken)

        }
}