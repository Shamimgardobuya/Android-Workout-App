package dev.shamy.splashingactivity.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import dev.shamy.splashingactivity.R
import dev.shamy.splashingactivity.models.ExerciseCategory
import dev.shamy.splashingactivity.models.ExerciseRequest

class ExerciseAdapter(context: Context, var exercises : List<ExerciseRequest>):
    ArrayAdapter<ExerciseRequest>(context, 0,exercises)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    fun getCustomView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_spinner_item, parent,false)
        val tvSpinnerText = view.findViewById<TextView>(R.id.tvSpinnerText)
        tvSpinnerText.text = exercises.get(position).exercise_name
        return view
    }

}