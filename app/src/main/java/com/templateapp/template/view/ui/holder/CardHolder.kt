package com.templateapp.template.view.ui.holder

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.template_android.R

class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val cardNumber: TextView = itemView.findViewById(R.id.card_number)
    val listCard: LinearLayout = itemView.findViewById(R.id.list_card_view)
    val cardImg: ImageView = itemView.findViewById(R.id.card_img)
    val cardDelete: ImageView = itemView.findViewById(R.id.card_delete)
}