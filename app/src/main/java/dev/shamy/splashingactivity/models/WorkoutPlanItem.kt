package dev.shamy.splashingactivity.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName ="WorkoutplanItem", indices = [Index(value=["workoutplanId","day"], unique = true)])
data class WorkoutPlanItem(
    @PrimaryKey var workoutPlanItemId:String,
    var workoutplanId:String,
    var day: Int,
    var exerciseIds:List<String>
)
