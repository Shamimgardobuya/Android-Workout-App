package dev.shamy.splashingactivity.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.shamy.splashingactivity.models.ExerciseCategory
import dev.shamy.splashingactivity.models.ExerciseRequest
import dev.shamy.splashingactivity.repository.ExerciseRepository
import kotlinx.coroutines.launch

class ExerciseViewModel : ViewModel(){

   val exerciseRepository=ExerciseRepository()
   lateinit var exerciseCategoryLiveData: LiveData<List<ExerciseCategory>>
   lateinit var exerciseLiveData: LiveData<List<ExerciseRequest>>
   val errorLiveData=MutableLiveData<String?>()


fun fetchExerciseCategories(accessToken:String) {
    viewModelScope.launch {
        val response = exerciseRepository.fetchExerciseCategories(accessToken)
        if (!response.isSuccessful) {
            errorLiveData.postValue(response.errorBody()?.string())
        }

    }
}

    fun fetchExercises(accessToken: String) {
        viewModelScope.launch {
            val response = exerciseRepository.fetchExercises(accessToken)
            if (!response.isSuccessful) {
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }


    }

    fun getDbCategories() {
        exerciseCategoryLiveData = exerciseRepository.getDbCategories()
    }

    fun getDbExercises() {
        exerciseLiveData = exerciseRepository.getDbExercises()

    }

    fun getExercisesByCategoryId(categoryId:String){
        exerciseLiveData=exerciseRepository.getExerciseByCategory(categoryId)
    }
}



