package com.templateapp.template.view.ui.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.template_android.R

class ChatHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val textBody: TextView = itemView.findViewById(R.id.text_message_body)
    val textTime: TextView = itemView.findViewById(R.id.text_message_time)
}