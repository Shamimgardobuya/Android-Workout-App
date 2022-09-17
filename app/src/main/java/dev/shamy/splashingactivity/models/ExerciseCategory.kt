package dev.shamy.splashingactivity.models

import com.google.gson.annotations.SerializedName

data class ExerciseCategory(
    @SerializedName("category_id") var categoryId:String,  //not exact way retrofit called it.
    @SerializedName("category_name") var categoryName:String,

)
