package dev.shamy.splashingactivity.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.ViewModelProvider
import dev.shamy.splashingactivity.databinding.FragmentProfileBinding
import dev.shamy.splashingactivity.models.ProfileRequest
import dev.shamy.splashingactivity.viewmodel.UserViewModel

class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    lateinit var sharedPrefs: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        sharedPrefs = activity?.applicationContext!!.getSharedPreferences(
            "WORKOUTLOG_PREFS",
            AppCompatActivity.MODE_PRIVATE
        )
        return  binding.root
    }
//        binding.tvLogout.setOnClickListener {
//            Logoutrequest()
//        }

//    }

//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.btnProfileLogin.setOnClickListener {
//            validatingProfile()
//        }
//
//    }
//
//    fun validatingProfile() {
//        var gender = binding.etGender.text.toString()
//        var dateofbirth = binding.etBirthDate.text.toString()
//        var weight = binding.etWeight.text.toString()
//        var height = binding.etHeight.text.toString()
//        var error = false
//
//        if (dateofbirth.isBlank()) {
//            binding.tilBirthDate.error = "Birth Date is required"
//            error=true
//        }
//        if (gender.isBlank()) {
//            binding.tilGender.error = "gender required"
//            error=true
//        }
//        if (weight.isBlank()) {
//            binding.tilWeight.error = "Your weight is required"
//            error=true
//
//        }
//        if (height.isBlank()) {
//            binding.tilHeight.error = "Your Email is required"
//            error=true
//        }
//        if (!error) {
//            val profilerequest = ProfileRequest(gender,dateofbirth,weight,height)
//            userViewModel.registerProfile(profilerequest)
//
//        }

//    }
    }

