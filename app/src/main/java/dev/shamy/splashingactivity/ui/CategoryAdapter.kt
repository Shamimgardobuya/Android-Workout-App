package dev.shamy.splashingactivity.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ArrayAdapter
import android.widget.TextView
import dev.shamy.splashingactivity.R
import dev.shamy.splashingactivity.models.ExerciseCategory
import java.text.FieldPosition

class CategoryAdapter(context: Context, var categories : List<ExerciseCategory>):
    ArrayAdapter<ExerciseCategory>(context, 0, categories) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    fun getCustomView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_spinner_item, parent,false)
        val tvSpinnerText = view.findViewById<TextView>(R.id.tvSpinnerText)
        tvSpinnerText.text = categories.get(position).categoryName
        return view
    }
}