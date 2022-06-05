package dev.shamy.splashingactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button

class MainActivity : AppCompatActivity() {
//    lateinit var  btnSt:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//    val intent=Intent(this,LoginActivity::class.java)
      startActivity(Intent(this,LoginActivity::class.java))
           finish()
//        setContentView(R.layout.activity_main)

//        btnSt=findViewById(R.id.btnSt)
//        btnSt.setOnClickListener {
//            val intent=Intent(this,LoginActivity::class.java)
//            startActivity(intent)
//            finish()
        }


        }

