package dev.shamy.splashingactivity.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.shamy.splashingactivity.models.WorkoutPlan


@Dao
interface WorkoutPlanDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkoutPlan(workoutPlan: WorkoutPlan)

    @Query("SELECT * FROM WorkoutPlan WHERE userId=:user_Id")
    fun getWorkoutPlanByUserId(user_Id:String)

}