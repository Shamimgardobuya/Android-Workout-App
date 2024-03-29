package dev.shamy.splashingactivity.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("phone_number")var phoneNumber:String,
    @SerializedName("first_name")var firstName:String,
    @SerializedName("last_name") var lastName:String,    //telling json,this hte kotlin object assignment
    var email:String,
   @SerializedName("user_id") var userId:String

)
