package dev.shamy.splashingactivity.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.LocusId
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.provider.SyncStateContract.Constants
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dev.shamy.splashingactivity.utils.Constants.Companion

import dev.shamy.splashingactivity.R
import dev.shamy.splashingactivity.databinding.FragmentTrackBinding
import dev.shamy.splashingactivity.models.WorkoutLogRecord
import dev.shamy.splashingactivity.viewmodel.WorkoutPlanViewModel
import java.time.LocalDate
import java.util.UUID


class TrackFragment : Fragment() {
    lateinit var binding: FragmentTrackBinding
    val workoutPlanViewModel: WorkoutPlanViewModel by viewModels()
    lateinit var prefs: SharedPreferences
    lateinit var userId: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding= FragmentTrackBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    @SuppressLint("NewApi")
    override fun onResume() {
        super.onResume()
        prefs=requireContext().getSharedPreferences(Companion.SHARED_PREFSFILE, Context.MODE_PRIVATE)
        userId=prefs.getString(Companion.USER_ID,Companion.EMPTYSTRING).toString()
        workoutPlanViewModel.getExistingWorkoutPlan(userId)
        workoutPlanViewModel.workoutPlanLiveData.observe(this, Observer {
            workoutPlan->
            val workoutPlanId= workoutPlan.workout_plan_id
            val dayNumber= LocalDate.now().dayOfWeek.value

            workoutPlanViewModel.getTodayWorkoutPlanItem(workoutPlanId,dayNumber).observe(this,
                Observer {
                    workoutPLanItem->
                    if(workoutPLanItem!=null){
                        workoutPlanItemId=workoutPLanItem.workoutPlanItemId
                        val todayExerciseIds=workoutPLanItem.exerciseIds
//                        exerciseViewModel
                    }
                    else{
                        Toast
                            .makeText(requireContext(),"No  workout plan item found for today ." +
                                    "Create one to proceed ",Toast.LENGTH_LONG)
                            .show()
                    }
                })
        })
        }
        override fun onClickDone(set:Int,weight:Int,reps:Int,exerciseId:String) {
            val workoutLogRecord = WorkoutLogRecord(
                workoutLogId = UUID.randomUUID().toString(),
                date = "",
                exerciseId = exerciseId,
                set = set,
                weight = weight,
                reps = reps,
                workoutPlanItemId = workoutPlanItemId,
                userId = userId
            )

        }


        }