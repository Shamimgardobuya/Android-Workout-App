package dev.shamy.splashingactivity.repository

import androidx.lifecycle.LiveData
import dev.shamy.splashingactivity.api.ApiClient
import dev.shamy.splashingactivity.api.ApiInterface
import dev.shamy.splashingactivity.database.Workoutdatabase
import dev.shamy.splashingactivity.models.ExerciseCategory
import dev.shamy.splashingactivity.WorkoutLog
import dev.shamy.splashingactivity.models.ExerciseRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

//import okhttp3.Response

class ExerciseRepository {

    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
    val database = Workoutdatabase.getDatabase(WorkoutLog.appContext)
    val exerciseCategoryDao = database.exerciseCategoryDao()
    val exerciseDao = database.exerciseDao()

   suspend fun fetchExerciseCategories(accessToken: String): Response<List<ExerciseCategory>> {
        return withContext(Dispatchers.IO) {
            var response = apiClient.fetchExerciseCategories(accessToken)
            if (response.isSuccessful) {
                var categories = response.body()
                categories?.forEach { category ->
                    exerciseCategoryDao.insertExerciseCategory(category)
                }
            }
            response
        }
    }

    suspend fun fetchExercises(accessToken: String): Response<List<ExerciseRequest>> {
        return withContext(Dispatchers.IO) {
            var response = apiClient.fetchExercises(accessToken)
            if (response.isSuccessful) {
                var exercises = response.body()
                exercises?.forEach { exercise ->
                    exerciseDao.insertExercise(exercise)
                }
            }
            response
        }
    }

    fun getDbCategories(): LiveData<List<ExerciseCategory>>{
        return exerciseCategoryDao.getExerciseCategories()
    }

    fun getDbExercises(): LiveData<List<ExerciseRequest>>{
        return exerciseDao.getExercises()
    }

    fun getExerciseByCategory(categoryId:String):LiveData<List<ExerciseRequest>>{
        return  exerciseDao.getExercisesByCategoryId(categoryId)

    }

}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    