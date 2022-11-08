package dev.shamy.splashingactivity.ui

import android.security.identity.AccessControlProfileId
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.shamy.splashingactivity.models.ExerciseRequest

class TrackAdapter (val exerciseList: List<ExerciseRequest>,val logWorkout: logWorkout):RecyclerView.Adapter<ExerciseViewHolder{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ExerciseViewHolder {
       val binding = RowExerciseNameBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentExercise = exerciseList.get(position)
        holder.binding.tvExerciseName.text = currentExercise.exercise_name
        holder.binding.tvSet1.checkBox.setOnClickListener{
            val weight=holder.binding.etWeight1.text.toString()
            val reps=holder.binding.etReps1.text.toString()
            logWorkout.onClickDone(
                set=1,
                weight=weight.toInt(),
                reps=reps.toInt(),
                currentExercise.exerciseId)
        }
        holder.binding.tvExerciseName.text = currentExercise.exercise_name
        holder.binding.tvSet1.checkBox2.setOnClickListener{
            val weight=holder.binding.etWeight2.text.toString()
            val reps=holder.binding.etReps2.text.toString()
            logWorkout.onClickDone(set=2,
                weight=weight.toInt(),
                reps=reps.toInt(),
                currentExercise.exerciseId)
        }

        holder.binding.tvExerciseName.text = currentExercise.exercise_name
        holder.binding.tvSet1.checkBox2.setOnClickListener{
            val weight=holder.binding.etWeight3.text.toString()
            val reps=holder.binding.etReps3.text.toString()
            logWorkout.onClickDone(set=3,weight=weight.toInt(),reps=reps.toInt(),currentExercise.exerciseId)
        }




    }

        override fun getItemCount(): Int {
            return exerciseList.size
        }
}
class  ExerciseViewHolder(val binding: RowExerciseNameBinding):RecyclerView.ViewHolder(binding.root)
interface  logWorkout{
    fun onClickDone(set:Int,weight:Int,reps:Int,exerciseId:String)
}
