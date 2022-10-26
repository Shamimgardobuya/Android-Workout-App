package dev.shamy.splashingactivity.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ExerciseCategory")
data class ExerciseCategory(
    @PrimaryKey @SerializedName("category_id") var categoryId:String,  //not exact way retrofit called it.
    @SerializedName("category_name") var categoryName:String,

    )
