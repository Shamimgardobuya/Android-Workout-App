package dev.shamy.splashingactivity.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.shamy.splashingactivity.R
import dev.shamy.splashingactivity.databinding.ActivityHomeBinding
import dev.shamy.splashingactivity.models.LoginResponse
import kotlin.math.log

class HomeActivity : AppCompatActivity() {
//    lateinit var bottom_navigation: BottomNavigationView
//    lateinit var fcvHome: FragmentContainerView
    lateinit var binding:ActivityHomeBinding
    lateinit var sharedPrefs:SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)   //used binding
        setContentView(binding.root)
        castViews()
        setupBottomNav()
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
