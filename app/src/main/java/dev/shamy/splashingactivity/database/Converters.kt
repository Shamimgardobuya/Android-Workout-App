package dev.shamy.splashingactivity.database


import androidx.room.TypeConverter
class Converters {
    @TypeConverter
    fun listToString(listX: List<String>): String {
        return listX.joinToString(",")

    }

    @TypeConverter
    fun stringToList(stringX: String) : List<String>{
        return stringX.split(",")
    }


}