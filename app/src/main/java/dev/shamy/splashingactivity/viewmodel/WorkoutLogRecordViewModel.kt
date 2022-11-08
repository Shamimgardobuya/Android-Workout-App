package dev.shamy.splashingactivity.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.shamy.splashingactivity.models.WorkoutLogRecord
import dev.shamy.splashingactivity.repository.WorkoutLogRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class WorkoutLogRecordViewModel: ViewModel() {
    val workoutLogRepository= WorkoutLogRepository()
    lateinit var todayRecordsLiveData:LiveData<List<WorkoutLogRecord>>
    fun saveWorkoutLogRecord(workoutLogRecord: WorkoutLogRecord){
        viewModelScope.launch {
            workoutLogRepository.saveWorkoutLOgRecord(workoutLogRecord)

        }




    }
    fun  getTodayWorkoutRecords(userId:String){
        todayRecordsLiveData=workoutLogRepository
            .getTodaysWorkoutRecord(userId,getCurrentDate())


    }
    fun getCurrentDate():String{
        val formatter= SimpleDateFormat("yyy-MM-dd", Locale.ENGLISH)
        val dateStr= formatter.format(Date())
        return formatter.format(Date())


    }

}