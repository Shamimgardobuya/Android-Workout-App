package dev.shamy.splashingactivity.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.shamy.splashingactivity.databinding.ActivityLoginBinding
import dev.shamy.splashingactivity.models.LoginRequest
import dev.shamy.splashingactivity.models.LoginResponse
import dev.shamy.splashingactivity.uutils.Constants
import dev.shamy.splashingactivity.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity() {
//    lateinit var btnLogin:Button
//    lateinit var tilEmail: TextInputLayout
//    lateinit var tilPassword:TextInputLayout
//    lateinit var etEmail: TextInputEditText
//    lateinit var etPassword:TextInputEditText
//    lateinit var tvSignup:TextView
//    lateinit var binding: Activ
      lateinit var binding:ActivityLoginBinding
      lateinit var sharedPrefs:SharedPreferences
      val userViewModel:UserViewModel by viewModels()   //instance of view,o

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
        binding=ActivityLoginBinding.inflate(layoutInflater)   //used binding
        setContentView(binding.root)
        sharedPrefs=getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)


        binding.tvSignup.setOnClickListener {
            val intent= Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            validateLogin()
//            val intent= Intent(this,SignupActivity::class.java)
//            startActivity(intent)

        }
    }

    override fun onResume() {///observing live datas
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer {  //prpoerty of viewmodle
            loginResponse->
            saveLoginDetails(loginResponse!!)

            Toast.makeText(baseContext,loginResponse?.message,Toast.LENGTH_LONG).show()
            binding.pbLogin.visibility= View.GONE

            startActivity(Intent(baseContext,HomeActivity::class.java))
            finish()

        })
        userViewModel.loginErrorLiveData.observe(this, Observer { error->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()

        })


    }
    fun validateLogin(){
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
        var error=false

        if (email.isBlank()){
            binding.tilEmail.error="Email is required"
            error=true
        }

        if (password.isBlank()){
            binding.tilPassword.error="Password is required"
            error=true

        }
        if(!error){
            var loginRequest=LoginRequest(email,password)
            binding.pbLogin.visibility=View.GONE
//            makeLoginRequest(loginRequest)
            userViewModel.loginUser(loginRequest)
        }

    }

    fun saveLoginDetails(loginResponse: LoginResponse){
     val editor= sharedPrefs.edit()
     val token = "Bearer ${loginResponse.accessToken}"

     editor.putString(Constants.accessToken,token)      //enable to use Bearer with shared preferences
     editor.putString(Constants.accessToken,loginResponse.userId)
     editor.putString(Constants.accessToken,loginResponse.userId)
     editor.apply()
        


    }

    }




