package com.templateapp.template.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template_android.R
import com.templateapp.template.view.ui.activities.Results
import com.templateapp.template.view.ui.holder.SearchHolder

class SearchAdapter(list: Results) : RecyclerView.Adapter<SearchHolder>() {

    private var mCardList: List<String> = list.listCards
    private val CLICKED_ITEM = 0
    private val NORMAL_ITEM = 1

    private var mCardPosition = list.position


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val context = parent.context
        return when (viewType) {
            CLICKED_ITEM -> {
                val view = LayoutInflater.from(context).inflate(R.layout.search_clicked_line_view, parent, false)
                SearchHolder(view)
            }
            NORMAL_ITEM -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.search_line_view, parent, false)
                SearchHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return mCardList.count()
    }


    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        holder.cardTitle.text = mCardList[position]
        holder.cardSubtitle.text = mCardList[position]
        holder.listCard.setOnClickListener {
            mCardPosition = position
            notifyDataSetChanged()
        }
    }

    override fun getItemViewType(position: Int): Int {
        val clicked = mCardPosition
        return if(clicked == position){
            CLICKED_ITEM
        } else{
            NORMAL_ITEM
        }

    }

    fun updateCards(list: List<String>) {
        mCardList = list
    }

}