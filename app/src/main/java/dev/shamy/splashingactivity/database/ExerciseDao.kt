package dev.shamy.splashingactivity.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.shamy.splashingactivity.models.ExerciseRequest


@Dao  //method for accessing object in database
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercise(exercise: ExerciseRequest)


    @Query("SELECT * FROM Exercise")
    fun getExercises(): LiveData<List<ExerciseRequest>>

    @Query("SELECT * FROM Exercise WHERE category_id= :selectedCategoryId")
    fun getExercisesByCategoryId(selectedCategoryId: String):LiveData<List<ExerciseRequest>>
}