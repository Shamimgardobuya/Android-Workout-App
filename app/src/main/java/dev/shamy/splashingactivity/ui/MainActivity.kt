package dev.shamy.splashingactivity.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    lateinit var sharedPrefs:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs=getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)
        val acessToken=sharedPrefs.getString("ACCESS_TOKEN","")
        if(acessToken!!.isNotBlank())
        {
            startActivity(Intent(this, HomeActivity::class.java))


        }
        else{
            startActivity(Intent(this,LoginActivity::class.java))
        }
        finish()


        }


        }

