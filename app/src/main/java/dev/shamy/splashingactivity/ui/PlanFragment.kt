package dev.shamy.splashingactivity.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.SyncStateContract.Constants
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import dev.shamy.splashingactivity.databinding.FragmentPlanBinding
import dev.shamy.splashingactivity.models.ExerciseCategory
import dev.shamy.splashingactivity.models.ExerciseRequest
import dev.shamy.splashingactivity.models.WorkoutPlan
import dev.shamy.splashingactivity.utils.Constants.Companion
import dev.shamy.splashingactivity.viewmodel.ExerciseViewModel
import dev.shamy.splashingactivity.viewmodel.WorkoutPlanViewModel
import java.util.*
import kotlin.collections.HashMap

class PlanFragment : Fragment() {
    val exerciseViewModel: ExerciseViewModel by viewModels()
    val workoutPlanViewModel:WorkoutPlanViewModel by viewModels()
    lateinit var exerciseLiveData: LiveData<List<ExerciseRequest>>
    lateinit var sharedPrefs: SharedPreferences

    var binding: FragmentPlanBinding? = null
    lateinit var workout_plan_id:String
    val bind get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentPlanBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onResume() {
        super.onResume()
        setupDaySpinner()
        exerciseViewModel.getDbCategories()
        setupCategorySpinner()
        setupExerciseSpinner()
        bind.btnAdd.setOnClickListener { clickAddItem() }
        checkForExistingWorkout()
    }

    fun setupDaySpinner(){
        val days = listOf("Select Day","Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        val daysAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, days)
        daysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bind.spDay.adapter = daysAdapter
    }

    fun setupCategorySpinner(){
        exerciseViewModel.exerciseCategoryLiveData.observe(this, Observer { categories->
            val firstCategory= ExerciseCategory("0","Select Category")
            val displayCategories= mutableListOf(firstCategory)
            displayCategories.addAll(categories)
            val categoryAdapter = CategoryAdapter(requireContext(), categories)
            bind.spCategory.adapter = categoryAdapter

            bind.spCategory.onItemSelectedListener = object :OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val selectedCategory=displayCategories.get(p2)
                    val categoryId=selectedCategory.categoryId
                    exerciseViewModel.getExercisesByCategoryId(categoryId)
                    setupExerciseSpinner()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        })
    }
    fun setupExerciseSpinner(){
             exerciseLiveData.observe(this, Observer { exercises->
                 val firstExercise=ExerciseRequest("0","","","Selecct Exercise","")
                 val exerciseAdapter=ExerciseAdapter(requireContext(),exercises)
                 bind.spMyexercise.adapter=exerciseAdapter

             })
//            exerciseViewModel.exerciseLiveData.observe(this, Observer { exercises->
//            val exerciseAdapter = ExerciseAdapter(requireContext(), exercises)
//            bind.spMyexercise.adapter = exerciseAdapter
//        })
    }
    fun clickAddItem(){
        var error=false
        if(bind.spDay.selectedItemPosition==0){
            error=true
            Toast.makeText(requireContext(),"Select day", Toast.LENGTH_LONG).show()


        }
        if(bind.spCategory.selectedItemPosition==0){
            error=true
            Toast.makeText(requireContext(),"Select day", Toast.LENGTH_LONG).show()


        }
        if(bind.spMyexercise.selectedItemPosition==0){
            error=true
            Toast.makeText(requireContext(),"Select day", Toast.LENGTH_LONG).show()


        }
        if (!error){
            val selectedExercise=bind.spMyexercise.selectedItem as ExerciseRequest
            val day=bind.spDay.selectedItem.toString()
            bind.spMyexercise.setSelection(0)
            bind.spCategory.setSelection(0)
            workoutPlanViewModel.selectedExerciseIds.add(selectedExercise.exerciseId)



        }



    }
    fun checkForExistingWorkout(){
        val prefs= requireActivity().getSharedPreferences(Companion.SHARED_PREFSFILE,Context.MODE_PRIVATE)
        val userId=prefs.getString(Companion.USER_ID ,Companion.EMPTYSTRING).toString()
        workoutPlanViewModel.getExistingWorkoutPlan(userId)
        workoutPlanViewModel.workoutPlanLiveData
            .observe(this,Observer{
                workoutPlan ->
                if(workoutPlan==null){
                    val newWorkoutPlan=WorkoutPlan(UUID.randomUUID().toString(),userId)
                    workoutPlanViewModel.saveWorkoutPlan(newWorkoutPlan)


                }
                else{
                    workout_plan_id=workoutPlan.workout_plan_id
                }

            })

    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
fun getDayNumber(day:String):Int?{
    val dayMap = HashMap<String,Int>()
    dayMap.put("Monday",1)
    dayMap.put("Tuesday",2)
    dayMap.put("Wednesday",3)
    dayMap.put("Thursday",4)
    dayMap.put("Friday",5)
    dayMap.put("Saturday",6)
    dayMap.put("Sunday",7)
    return dayMap.get(day) ?:-1
}
    fun clickSaveDay() {
        if(bind.spDay.selectedItemPosition==0){
            Toast.makeText(requireContext(),"Selected Day",Toast.LENGTH_LONG).show()
            return
        }
        val day = bind.spDay.selectedItem.toString()
        val dayNumber=getDayNumber(day)
       if( workoutPlanViewModel.selectedExerciseIds.isEmpty()){
           Toast.makeText(requireContext(),"Select some exercises for the $day",Toast.LENGTH_LONG).show()
           return
       }
    }
}