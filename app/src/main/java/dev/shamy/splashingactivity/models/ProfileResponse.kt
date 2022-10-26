package dev.shamy.splashingactivity.models

import com.google.gson.annotations.SerializedName

class ProfileResponse (
    @SerializedName("date_of_birth") var birthDate:String,
    @SerializedName("gender")var gender:String,
    @SerializedName("weight") var weight:String,
    @SerializedName("height")var height:String,


    )