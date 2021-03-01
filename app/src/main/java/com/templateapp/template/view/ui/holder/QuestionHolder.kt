package com.templateapp.template.view.ui.holder

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.template_android.R

class QuestionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val questionResume: TextView = itemView.findViewById(R.id.question_resume)
    val listItem: LinearLayout = itemView.findViewById(R.id.list_item)

}