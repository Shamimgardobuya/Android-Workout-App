package dev.shamy.splashingactivity.repository

import androidx.lifecycle.LiveData
import dev.shamy.splashingactivity.WorkoutLog
import dev.shamy.splashingactivity.database.Workoutdatabase
import dev.shamy.splashingactivity.models.WorkoutLogRecord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class WorkoutLogRepository {
   val database=Workoutdatabase.getDatabase(WorkoutLog.appContext)
    val workoutLogRecordDao=database.WorkoutLogRecordDao()
    suspend fun saveWorkoutLOgRecord(workoutLogRecord: WorkoutLogRecord){


        withContext(Dispatchers.IO){
            workoutLogRecord
        }
    }






    fun getTodaysWorkoutRecord(userId:String, currentDate: String):LiveData<List<WorkoutLogRecord>>{
        return workoutLogRecordDao.get

    }

}