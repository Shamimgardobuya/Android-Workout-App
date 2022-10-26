package dev.shamy.splashingactivity.models

import com.google.gson.annotations.SerializedName

data class ExerciseResponse (
     var message:String,
     @SerializedName ("access_token")var accessToken:String,
     @SerializedName ("exercise_id")var ExerciseId:String,
         )