package dev.shamy.splashingactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    lateinit var btnLogin:Button
    lateinit var tilEmail: TextInputLayout
    lateinit var tilPassword:TextInputLayout
    lateinit var etEmail: TextInputEditText
    lateinit var etPassword:TextInputEditText
    lateinit var tvSignup:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogin=findViewById(R.id.btnLogin)
        tilEmail=findViewById(R.id.tilEmail)
        tilPassword=findViewById(R.id.tilPassword)
        etEmail=findViewById(R.id.etEmail)
        etPassword=findViewById(R.id.etPassword)
        tvSignup=findViewById(R.id.tvSignup)

        btnLogin.setOnClickListener {
//            val intent=Int
            validateLogin()
        }
        tvSignup.setOnClickListener{
            val intent=Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }



//        val intent=Intent(this,LoginActivity::class.java)
//        startActivity(intent)
//        finish()
    }
    fun validateLogin(){
        var email=etEmail.text.toString()
        var password=etPassword.text.toString()
        if (email.isBlank()) {
            tilEmail.error = getString(R.string.Email_is_required) //getString


        }
        if(password.isBlank()){
            tilPassword.error=getString(R.string.Password_required)
        }
    }
}