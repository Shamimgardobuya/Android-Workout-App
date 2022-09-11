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
import dev.shamy.splashingactivity.api.ApiClient
import dev.shamy.splashingactivity.api.ApiInterface
import dev.shamy.splashingactivity.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
//    fun makeLoginRequest(loginRequest:LoginRequest){
//        var apiClient=ApiClient.buildApiClient(ApiInterface::class.java)
//        var request=apiClient.login(loginRequest)
//
//        request.enqueue(object : Callback<LoginResponse> {
//            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                if (response.isSuccessful){
//                //data class for res
//                    val loginResponse= response.body()
//                    saveLoginDetails(loginResponse!!)
//
//                    Toast.makeText(baseContext,loginResponse?.message,Toast.LENGTH_LONG).show()
//                    binding.pbLogin.visibility= View.GONE
//
//                    startActivity(Intent(baseContext,HomeActivity::class.java))
//                    finish()
//                }
//                else{
//                    val error=response.errorBody()?.string()
//                    Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
//
//                }
//            }
//
//            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                binding.pbLogin.visibility= View.GONE
//
//                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
//
//            }
//        })
//    }
    fun saveLoginDetails(loginResponse: LoginResponse){
     val editor= sharedPrefs.edit()
     editor.putString("ACCESS_TOKEN",loginResponse.accessToken)
     editor.putString("USER_ID",loginResponse.userId)
     editor.putString("PROFILE_ID",loginResponse.userId)
     editor.apply()
        


    }
    fun logout(){
//        binding.btnLogout.setOnClickListener( new OnClickListener() {

//
//            val public = null
//            @Override
//            public void onClick(View arg0) {
//                SharedPreferences  = getSharedPreferences("Activity",  //small way of storing data to retrieve it
//                MODE_PRIVATE);
//                SharedPreferences.Editor editor = myPrefs.edit();
//                editor.clear();   //cleaars all shared preferences
//                editor.commit();
//                //AppState.getSingleInstance().setLoggingOut(true);
//                setLoginState(true);
//                Log.d(TAG, "Now log out and start the activity login");
//                Intent intent = new Intent(HomeActivity.this,
//                    LoginActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//
//            }
//        });
//
//        private void setLoginState(boolean status) {
//            SharedPreferences sp = getSharedPreferences("LoginState",
//            MODE_PRIVATE);
//            SharedPreferences.Editor ed = sp.edit();
//            ed.putBoolean("setLoggingOut", status);
//            ed.commit();
//        }
    }

}


