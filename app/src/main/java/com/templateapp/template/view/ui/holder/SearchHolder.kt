package com.templateapp.template.view.ui.holder

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.template_android.R

class SearchHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val cardTitle: TextView = itemView.findViewById(R.id.card_title)
    val cardSubtitle: TextView = itemView.findViewById(R.id.card_subtitle)
    val listCard: LinearLayout = itemView.findViewById(R.id.list_card_view)
}