package dev.shamy.splashingactivity.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.shamy.splashingactivity.models.WorkoutLogRecord
import java.util.*


@Dao
interface WorkoutLogRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertworkoutLogRecord(workoutLog: WorkoutLogRecord)


    @Query("SELECT*FROM workoutlogrecord WHERE userId=:userId AND date >= :currentDate ")
    fun getworkoutLogsByUserId(userId:String,currentDate: String): LiveData<List<WorkoutLogRecord>>

}