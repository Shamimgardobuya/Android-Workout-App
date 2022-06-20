package dev.shamy.splashingactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.shamy.splashingactivity.databinding.ActivityLoginBinding

class HomeActivity : AppCompatActivity() {
    lateinit var bottom_navigation: BottomNavigationView
    lateinit var fcvHome: FragmentContainerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        castViews()
        setupBottomNav()

    }

    fun castViews() {
        bottom_navigation = findViewById(R.id.bnvHome)  //to cast them
        fcvHome = findViewById(R.id.fcvHome)

    }

    fun setupBottomNav() {
        bottom_navigation.setOnItemSelectedListener { item ->  //
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


}
