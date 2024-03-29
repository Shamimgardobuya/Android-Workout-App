package dev.shamy.splashingactivity.ui

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.shamy.splashingactivity.R
import dev.shamy.splashingactivity.databinding.ActivityHomeBinding
import dev.shamy.splashingactivity.uutils.Constants
import dev.shamy.splashingactivity.viewmodel.ExerciseViewModel

class HomeActivity : AppCompatActivity() {

    lateinit var binding:ActivityHomeBinding
    lateinit var sharedPrefs:SharedPreferences
    val exerciseViewModel:ExerciseViewModel by viewModels()
    lateinit var sharedPreferences: SharedPreferences
    lateinit var token:String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)   //used binding
        setContentView(binding.root)
        castViews()
        setupBottomNav()
        sharedPrefs=getSharedPreferences(Constants.prefsFile, MODE_PRIVATE)
        token = sharedPrefs.getString(Constants.accessToken, "").toString()

        exerciseViewModel.getDbCategories()
        exerciseViewModel.getDbExercises()



    }

    override fun onResume() {
        super.onResume()
        exerciseViewModel.exerciseCategoryLiveData.observe(this, Observer { categories->
            if (categories.isEmpty()){
                exerciseViewModel.fetchExerciseCategories(token)
            }
        })

        exerciseViewModel.exerciseLiveData.observe(this, Observer { exercises->
            if (exercises.isEmpty()){
                exerciseViewModel.fetchExercises(token)
            }
        })

        exerciseViewModel.errorLiveData.observe(this, Observer { error->
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        })

    }


    fun castViews() {
     binding.bnvHome
     binding.fcvHome

    }

    fun setupBottomNav() {
        binding.bnvHome.setOnItemSelectedListener { item ->  //
            when (item.itemId) {
                R.id.plan -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, PlanFragment())
                        .commit()
                    true
                }
                R.id.track -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, TrackFragment())
                        .commit()
                    true
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fcvHome,ProfileFragment()).commit()
                    true
                }
                else -> false
            }
        }

    }

}
