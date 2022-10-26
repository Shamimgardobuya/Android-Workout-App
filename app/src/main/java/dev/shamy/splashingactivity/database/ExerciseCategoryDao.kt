package dev.shamy.splashingactivity.database

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.shamy.splashingactivity.models.ExerciseCategory
import dev.shamy.splashingactivity.models.ExerciseRequest

@Dao
interface ExerciseCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExerciseCategory(exercise: ExerciseCategory)


    @Query("SELECT * FROM ExerciseCategory")
    fun getExerciseCategories():LiveData<List<ExerciseCategory>>



}