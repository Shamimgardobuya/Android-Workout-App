package dev.shamy.splashingactivity.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "Exercise")
data class ExerciseRequest(
  @PrimaryKey @SerializedName("exercise_id") var exerciseId:String,

  @SerializedName("categoryId")  var category_id:String,

  @SerializedName ("exercise_name")var exercise_name:String,

  var image : String?,
  var description: String?,
)

