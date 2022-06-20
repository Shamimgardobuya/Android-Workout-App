package dev.shamy.splashingactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.shamy.splashingactivity.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
//    lateinit var tvLogin2:TextView
//    lateinit var btnSigning:Button
//    lateinit var tilFirstName:TextInputLayout
//    lateinit var etFirstName: TextInputEditText
//    lateinit var tilLastName:TextInputLayout
//    lateinit var etLastName: TextInputEditText
//    lateinit var tilEmail2:TextInputLayout
//    lateinit var etEmail2:TextInputEditText
//    lateinit var tilPassword2:TextInputLayout
//    lateinit var etPassword2:TextInputEditText
//    lateinit var etConfirm:TextInputEditText
//    lateinit var tilConfirm:TextInputLayout
    lateinit var binding:ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          binding=ActivitySignupBinding.inflate(layoutInflater)   //used binding
          setContentView(binding.root)
//        setContentView(R.layout.activity_signup)
//        tilFirstName=findViewById(R.id.tilFirstName)
//        etFirstName=findViewById(R.id.etFirsName)
//        tilLastName=findViewById(R.id.tilLastName)
//        etLastName=findViewById(R.id.etLastName)
//        tilEmail2=findViewById(R.id.tilEmail2)
//        etEmail2=findViewById(R.id.etEmail2)
//        tilPassword2=findViewById(R.id.tilPassword2)
//        etPassword2=findViewById(R.id.etPassword2)
//        etConfirm=findViewById(R.id.etConfirm)
//        tilConfirm=findViewById(R.id.tilConfirm)
//        btnSigning=findViewById(R.id.btnSigning)
//        tvLogin2=findViewById(R.id.tvLogin2)
//        btnSigning.setOnClickListener {
//               validatingUser()
        binding.tvLogin2.setOnClickListener{
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnSigning.setOnClickListener {
            validatingUser()
            val intent= Intent(this,HomeActivity::class.java)
            startActivity(intent)

        }





    }
    fun validatingUser(){
        var email=binding.etEmail2.text.toString()
        var password2=binding.etPassword2.text.toString()
        var firstNa=binding.etFirsName.text.toString()
        var lastNa=binding.etLastName.text.toString()
        var confirMing=binding.etConfirm.text.toString()



        if (email.isBlank()) {
            binding.tilEmail2.error = getString(R.string.Email_is_required) //getString


        }
        if(password2.isBlank()){
            binding.tilPassword2.error=getString(R.string.Password_required)
        }
        if(firstNa.isBlank()){
            binding.tilFirstName.error=getString(R.string.first_name_required)
        }
        if(lastNa.isBlank()){
            binding.tilLastName.error=getString(R.string.Last_name_required)
        }
        if(confirMing.isBlank()){
            binding.tilConfirm.error="Please input other fields"
        }
        if(password2!=confirMing){                                      //if passwod does not match when confirming
            binding.tilPassword2.error="Password does not match"
        }
       if (password2.length<8){              // if passwordn is too short
           println("Password is too short")
       }
       if (password2.length>16){                             //when password is too long.
           println("Your password is too long")

       }

    }
}