package com.templateapp.template.view.ui.holder

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.template_android.R

class ExtractHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val totalPrice: TextView = itemView.findViewById(R.id.total_price)
    val date: TextView = itemView.findViewById(R.id.date)
    val detailsName: TextView = itemView.findViewById(R.id.details_name)
    val listCard: LinearLayout = itemView.findViewById(R.id.list_item)
}