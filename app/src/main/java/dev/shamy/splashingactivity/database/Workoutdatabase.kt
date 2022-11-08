package dev.shamy.splashingactivity.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.shamy.splashingactivity.models.ExerciseCategory
import dev.shamy.splashingactivity.models.ExerciseRequest
import dev.shamy.splashingactivity.models.WorkoutPlan
import dev.shamy.splashingactivity.models.WorkoutPlanItem
import java.math.MathContext



@Database(
    entities = arrayOf(
        ExerciseCategory::class,
        ExerciseRequest::class,
        WorkoutPlan::class,
        WorkoutPlanItem::class
    ), version = 5
)
@TypeConverters(Converters::class)
abstract class Workoutdatabase:RoomDatabase () {
    @TypeConverters(Converters::class)
    //one instance of database at any given mmoment
    abstract fun exerciseCategoryDao():ExerciseCategoryDao
    abstract fun exerciseDao():ExerciseDao
    abstract fun WorkoutLogRecordDao(): WorkoutLogRecordDao
    abstract fun WorkoutPlanItemDao(): WorkoutPlanItemDao
    abstract fun WorkoutPlanDao(): WorkoutPlanDao

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
