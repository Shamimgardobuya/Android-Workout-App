package dev.shamy.splashingactivity.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.shamy.splashingactivity.models.WorkoutPlanItem
import java.sql.RowId

@Dao
interface WorkoutPlanItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertWorkoutPlanItem(workoutPlanItem: WorkoutPlanItem)

    @Query("SELECT * FROM workoutplanitem WHERE workoutplanId = :workoutPlanId AND  day=:dayNumber")
    fun getWorkoutPlanItem(workoutPlanId: String,dayNumber: Int):LiveData<WorkoutPlanItem>

}