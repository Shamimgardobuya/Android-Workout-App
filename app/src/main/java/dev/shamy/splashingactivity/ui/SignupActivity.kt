package dev.shamy.splashingactivity.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.shamy.splashingactivity.R
import dev.shamy.splashingactivity.databinding.ActivitySignupBinding
import dev.shamy.splashingactivity.models.RegisterResponse
import dev.shamy.splashingactivity.models.ReisterRequest
import dev.shamy.splashingactivity.api.ApiClient
import dev.shamy.splashingactivity.api.ApiInterface
import dev.shamy.splashingactivity.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {

    lateinit var binding:ActivitySignupBinding
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

          binding=ActivitySignupBinding.inflate(layoutInflater)   //used binding
          setContentView(binding.root)

        binding.tvLogin2.setOnClickListener{
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnSigning.setOnClickListener {
            validatingUser()
          

        }





    }
    override fun onResume() {///observing live datas
        super.onResume()
        userViewModel.registerResponseLiveData.observe(this, Observer {  //prpoerty of viewmodle
                registerResponse->
//        saef(!!)

            Toast.makeText(baseContext,registerResponse?.message,Toast.LENGTH_LONG).show()


            startActivity(Intent(baseContext,LoginActivity::class.java))
            finish()

        })
        userViewModel.registerErrorLiveData.observe(this, Observer { error->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
        })


    }



    fun validatingUser(){
        var email=binding.etEmail2.text.toString()
        var password2=binding.etPassword2.text.toString()
        var firstNa=binding.etFirsName.text.toString()
        var lastNa=binding.etLastName.text.toString()
        var confirMing=binding.etConfirm.text.toString()
        var phoneNumber=binding.etPhoneNumber.text.toString()

         var error=false

        if (email.isBlank()) {
            error= true
            binding.tilEmail2.error = getString(R.string.Email_is_required) //getString


        }
        if(password2.isBlank()){
            error=true
            binding.tilPassword2.error=getString(R.string.Password_required)
        }
        if(firstNa.isBlank()){
            error=true
            binding.tilFirstName.error=getString(R.string.first_name_required)
        }
        if(lastNa.isBlank()){
            error=true
            binding.tilLastName.error=getString(R.string.Last_name_required)
        }
        if(confirMing.isBlank()){
            error=true
            binding.tilConfirm.error="Please input other fields"
        }
        if(password2!=confirMing){                                      //if passwod does not match when confirming
            error=true
            binding.tilPassword2.error="Password does not match"
        }
       if (password2.length<8){              // if passwordn is too short
           error=true
           println("Password is too short")
       }
       if (password2.length>16){
           //when password is too long.
           println("Your password is too long")

       }
        if(!error){
            val registerRequest=ReisterRequest(email=email, password = password2, firstName = firstNa, lastName = lastNa, phoneNumber = phoneNumber)
            userViewModel.registerUser(registerRequest)


        }

    }

}