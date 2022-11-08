package dev.shamy.splashingactivity.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName


@Entity (tableName = "WorkoutPlan")
data class WorkoutPlan(
   @PrimaryKey var workout_plan_id:String,
   var userId:String


)
