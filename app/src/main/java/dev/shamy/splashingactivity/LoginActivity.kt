package dev.shamy.splashingactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.shamy.splashingactivity.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
//    lateinit var btnLogin:Button
//    lateinit var tilEmail: TextInputLayout
//    lateinit var tilPassword:TextInputLayout
//    lateinit var etEmail: TextInputEditText
//    lateinit var etPassword:TextInputEditText
//    lateinit var tvSignup:TextView
//    lateinit var binding: Activ
      lateinit var binding:ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
        binding=ActivityLoginBinding.inflate(layoutInflater)   //used binding
        setContentView(binding.root)
        binding.tvSignup.setOnClickListener {
            val intent= Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            validateLogin()
            val intent= Intent(this,HomeActivity::class.java)
            startActivity(intent)

        }
    }
    fun validateLogin(){
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()

        if (email.isBlank()){
            binding.tilEmail.error="Email is required"
        }

        if (password.isBlank()){
            binding.tilPassword.error="Password is required"
        }

    }

}


