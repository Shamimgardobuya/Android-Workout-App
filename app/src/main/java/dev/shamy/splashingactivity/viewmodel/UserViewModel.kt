package dev.shamy.splashingactivity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.shamy.splashingactivity.models.*
import dev.shamy.splashingactivity.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {

     val userRepository=UserRepository()
     var loginResponseLiveData= MutableLiveData<LoginResponse>()  //obser
     val loginErrorLiveData = MutableLiveData<String?>()  //for failure
     var registerResponseLiveData= MutableLiveData<RegisterResponse>()
     val registerErrorLiveData = MutableLiveData<String?>()
     val profileResponseLiveData=MutableLiveData<ProfileResponse>()
     val profileErrorLiveData=MutableLiveData<String>()


    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response=userRepository.login(loginRequest)
            if (response.isSuccessful){
                loginResponseLiveData.postValue(response.body())

            }
            else{
                val error = response.errorBody()?.string()
                loginErrorLiveData.postValue(error)
            }

        }

    }
    fun registerUser(registerRequest: ReisterRequest){
        viewModelScope.launch {
            val response=userRepository.registerUser(registerRequest)
            if (response.isSuccessful){
                registerResponseLiveData.postValue((response.body()))

            }
            else{
                val error = response.errorBody()?.string()
                registerErrorLiveData.postValue(error)
            }
        }
    }
//    fun registerProfile(profileRequest: ProfileRequest){
//        viewModelScope.launch {
//            val response=userRepository.profileMe(profileRequest)
//            if(response.isSuccessful){
//                profileResponseLiveData.postValue(response.body())
//            }
//            else{
//                val error = response.errorBody()?.string()
//                profileErrorLiveData.postValue(error)
//            }
//        }
//    }

}