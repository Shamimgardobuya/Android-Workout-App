package dev.shamy.splashingactivity.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.shamy.splashingactivity.models.WorkoutPlan
import dev.shamy.splashingactivity.models.WorkoutPlanItem
import dev.shamy.splashingactivity.repository.WorkoutPlanRepostory
import kotlinx.coroutines.launch
import java.util.UUID

class WorkoutPlanViewModel:ViewModel() {
    val workoutPlanRepository=WorkoutPlanRepostory()
    lateinit var workoutPlanLiveData : LiveData<WorkoutPlan>
    var selectedExerciseIds=mutableListOf<String>()



    fun saveWorkoutPlan(workoutPlan: WorkoutPlan){
        viewModelScope.launch {
            workoutPlanRepository.saveWorkoutPlan(workoutPlan)
        }

    }
    fun getExistingWorkoutPlan(userId:String){
        workoutPlanLiveData
    }
    fun createWorkoutPlanItem(dayNumber:Int, workout_plan_id:String){
        val workoutPlanItem = WorkoutPlanItem(
            workoutPlanItemId= UUID.randomUUID().toString(),
            workoutplanId= workout_plan_id,
            day= dayNumber,
            exerciseIds = selectedExerciseIds
        )
        viewModelScope.launch {
            workoutPlanRepository.saveWorkoutPlanItem(workoutPlanItem)
        }
    }
    fun getTodayWorkoutPlanItem(workoutPlanId:String,dayNumber: Int):LiveData<WorkoutPlanItem>{
        return workoutPlanRepository.getTodayWorkoutPlanItem(workoutPlanId,dayNumber)

    }

}