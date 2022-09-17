package dev.shamy.splashingactivity.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.shamy.splashingactivity.R
import dev.shamy.splashingactivity.databinding.ActivityHomeBinding
import dev.shamy.splashingactivity.models.LoginResponse
import dev.shamy.splashingactivity.util.Constants
import dev.shamy.splashingactivity.viewmodel.ExerciseViewModel
import kotlin.math.log

class HomeActivity : AppCompatActivity() {

    lateinit var binding:ActivityHomeBinding
    lateinit var sharedPrefs:SharedPreferences
    val exerciseViewModel:ExerciseViewModel by viewModels()
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)   //used binding
        setContentView(binding.root)
        castViews()
        setupBottomNav()
        sharedPrefs=getSharedPreferences(Constants.prefsFile, MODE_PRIVATE)
        val token=sharedPrefs.getString(Constants.accessToken,Constants.EMPTY_STRING)
        exerciseViewModel.fetchExerciseCategories(token!!) //never be null

        binding.tvLogout.setOnClickListener {
            val editor=sharedPrefs.edit()
            editor.putString("ACCESS_TOKEN","")
            editor.putString("USER_ID","")
            editor.putString("PROFILE_ID","")
            editor.apply()
//            startActivity(Intent(this,LoginResponse::class.java))
            startActivity(Intent(this,LoginResponse::class.java))
            logOutrequest()

        }
    }

    override fun onResume() {
        super.onResume()
        exerciseViewModel.exerciseCategoryLiveData.observe(this, Observer {
            exerciseCateg->

            Toast.makeText( baseContext,"fetached ${exerciseCateg.size} categosries ",
            Toast.LENGTH_LONG
                ).show()
        }  )
        exerciseViewModel.errorLiveData.observe(this, Observer {
            error->
            Toast.makeText(this,error,Toast.LENGTH_LONG).show()


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
                        .replace(R.id.fcvHome, ProfileFragment()).commit()
                    true
                }
                else -> false
            }
        }

    }
fun logOutrequest(){
    sharedPrefs.edit().clear().commit()
}

}
