package com.templateapp.template.view.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template_android.R
import com.templateapp.template.services.actions.Utils
import com.templateapp.template.view.ui.activities.settings.SecondarySupportActivity
import com.templateapp.template.view.ui.fragments.support.Extract
import com.templateapp.template.view.ui.holder.ExtractHolder

class ExtractAdapter(list: Extract, context: Context, activity: Activity) :
    RecyclerView.Adapter<ExtractHolder>() {
    private var mNameList: List<String> = list.nameDetails
    private var mDateList: List<String> = list.data
    private var mPriceList: List<Int> = list.price
    private var mActivity: Activity = activity
    private var mContext: Context = context
    private val CLICKED_ITEM = 0
    private val NORMAL_ITEM = 1
    private var clicked = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExtractHolder {
        val context = parent.context
        return when (viewType) {
            CLICKED_ITEM -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.list_extract_clicked_view, parent, false)
                ExtractHolder(view)
            }
            NORMAL_ITEM -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_extract_view, parent, false)
                ExtractHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return mNameList.count()
    }


    override fun onBindViewHolder(holder: ExtractHolder, position: Int) {
        holder.totalPrice.text = "R$" + mPriceList[position] + ",00"
        holder.date.text = mDateList[position]
        holder.detailsName.text = mNameList[position]
        holder.listCard.setOnClickListener {
            mActivity.findViewById<View>(R.id.loading_faq).visibility = View.VISIBLE
            clicked = true
            notifyDataSetChanged()
            delay(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (clicked) {
            CLICKED_ITEM
        } else {
            NORMAL_ITEM
        }

    }

    fun updateQuestions(list: List<String>) {
        mNameList = list
    }

    fun delay(position: Int) {
        Handler().postDelayed(
            {
                val supportClient = Intent(mActivity, SecondarySupportActivity::class.java)
                supportClient.putExtra("name", mNameList[position])
                supportClient.putExtra("date", mDateList[position])
                supportClient.putExtra("price", mPriceList[position])
                mContext.startActivity(supportClient)
                mActivity.findViewById<View>(R.id.loading_faq).visibility = View.GONE
                clicked = false
                notifyDataSetChanged()
            },
            500
        )
    }

}