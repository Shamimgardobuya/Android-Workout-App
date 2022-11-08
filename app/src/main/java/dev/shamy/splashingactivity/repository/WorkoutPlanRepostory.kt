package dev.shamy.splashingactivity.repository

import androidx.lifecycle.LiveData
import dev.shamy.splashingactivity.WorkoutLog
import dev.shamy.splashingactivity.database.Workoutdatabase
import dev.shamy.splashingactivity.models.WorkoutPlan
import dev.shamy.splashingactivity.models.WorkoutPlanItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class WorkoutPlanRepostory {
    val database=Workoutdatabase.getDatabase(WorkoutLog.appContext)
    val workoutPlanDao=database.WorkoutPlanDao()
    val workoutPlanItemDao = database.WorkoutPlanItemDao()


    suspend fun saveWorkoutPlan(workoutPlan: WorkoutPlan){
        withContext(Dispatchers.IO){
            workoutPlanDao.insertWorkoutPlan(workoutPlan)

        }
    }
    suspend fun saveWorkoutPlanItem(workoutPlanItem: WorkoutPlanItem){
        withContext(Dispatchers.IO){
            workoutPlanItemDao.insertWorkoutPlanItem(workoutPlanItem)
    }
    }

    fun getWorkoutPlanByUserId (userId: String) {
        return  workoutPlanDao.getWorkoutPlanByUserId(userId)
    }

    fun getTodayWorkoutPlanItem(workoutPlanId:String, dayNumber:Int):LiveData<WorkoutPlanItem>{
        return  workoutPlanItemDao.getTodayWorkoutPlanItem(workoutPlanId,dayNumber)
    }
}