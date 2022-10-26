package dev.shamy.splashingactivity.ui

import android.os.Bundle
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
import dev.shamy.splashingactivity.R
import dev.shamy.splashingactivity.databinding.FragmentPlanBinding
import dev.shamy.splashingactivity.databinding.FragmentProfileBinding
import dev.shamy.splashingactivity.models.ExerciseCategory
import dev.shamy.splashingactivity.models.ExerciseRequest
import dev.shamy.splashingactivity.viewmodel.ExerciseViewModel

class PlanFragment : Fragment() {
    val exerciseViewModel: ExerciseViewModel by viewModels()
    lateinit var exerciseLiveData: LiveData<List<ExerciseRequest>>

    var binding: FragmentPlanBinding? = null
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
                 val firstExercise=ExerciseRequest("0","","","Selecct Exercise")
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
            exerciseViewModel.selectedExerciseIds()





        }



    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}