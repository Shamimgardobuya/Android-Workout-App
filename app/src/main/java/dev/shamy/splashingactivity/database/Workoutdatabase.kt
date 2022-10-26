package dev.shamy.splashingactivity.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.shamy.splashingactivity.models.ExerciseCategory
import dev.shamy.splashingactivity.models.ExerciseRequest
import java.math.MathContext

@Database(entities = arrayOf(ExerciseCategory::class, ExerciseRequest::class), version = 2)
abstract class Workoutdatabase:RoomDatabase () {
    //one instance of database at any given mmoment
    abstract fun exerciseCategoryDao():ExerciseCategoryDao
    abstract fun exerciseDao():ExerciseDao

    companion object {
        private var database:Workoutdatabase?=null
        fun getDatabase(context: Context):Workoutdatabase{
            if(database==null){
                database= Room
                    .databaseBuilder(context,Workoutdatabase::class.java,"Workoutdatabase")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return database as Workoutdatabase
        }
    }


}
