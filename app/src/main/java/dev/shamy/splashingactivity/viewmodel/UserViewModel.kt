package dev.shamy.splashingactivity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.shamy.splashingactivity.models.LoginRequest
import dev.shamy.splashingactivity.models.LoginResponse
import dev.shamy.splashingactivity.models.RegisterResponse
import dev.shamy.splashingactivity.models.ReisterRequest
import dev.shamy.splashingactivity.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {

     val userRepository=UserRepository()
//     val userRegister=RegisterRepository()
     var loginResponseLiveData= MutableLiveData<LoginResponse>()  //obser
     val loginErrorLiveData = MutableLiveData<String?>()  //for failure
     var registerResponseLiveData= MutableLiveData<RegisterResponse>()
     val registerErrorLiveData = MutableLiveData<String?>()

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
                val error = response.errorBody()?.string()
                registerErrorLiveData.postValue(error)
            }
            else{
                val error = response.errorBody()?.string()
                registerErrorLiveData.postValue(error)
            }
        }
    }

}