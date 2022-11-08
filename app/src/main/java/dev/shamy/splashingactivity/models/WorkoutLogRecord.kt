package dev.shamy.splashingactivity.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity
data class WorkoutLogRecord(
    @PrimaryKey var workoutLogId:String,
    var date: String,
    var exerciseId:String,
    var userId:String,
    var set:String,
    var workoutLogItemId:String,



    )
