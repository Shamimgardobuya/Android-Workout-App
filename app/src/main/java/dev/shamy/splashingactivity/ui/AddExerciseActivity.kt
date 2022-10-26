package dev.shamy.splashingactivity.ui//package dev.shamy.splashingactivity.ui//package dev.shamy.splashingactivity.ui
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.activity.viewModels
//import dev.shamy.splashingactivity.R
//import dev.shamy.splashingactivity.databinding.ActivityAddExerciseBinding
//import dev.shamy.splashingactivity.models.ExerciseCategory
//import dev.shamy.splashingactivity.viewmodel.ExerciseViewModel
//
//class AddExerciseActivity : AppCompatActivity() {
//    lateinit var binding: ActivityAddExerciseBinding
//    val exerciseViewmodel:ExerciseViewModel by viewModels()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding=ActivityAddExerciseBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//    }
//
//    override fun onResume() {
//        super.onResume()
//        binding.btnSave.setOnClickListener { validateAddcontact() }
//    }
//    fun validateAddcontact(){
//        var exercises=binding.etExercises.text.toString()
//
//        var error=false
//
//
//        if (exercises.isBlank()){
//            binding.etExercises.error="name required"
//            error=true
//        }
//
//        if(!error){
//            startActivity(Intent(this,MainActivity::class.java))
//        }
//
//
//        val exercises=ExerciseCategory(categoryId ="0", categoryName = "categoryName")
//        exerciseViewmodel.saveContact(contact)
//        finish()
//    }
//}
//}
