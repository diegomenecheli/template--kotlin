package com.templateapp.template.view.adapter


import android.app.Activity
import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.template_android.R
import com.templateapp.template.services.actions.Utils
import com.templateapp.template.view.ui.activities.Payments.Cards
import com.templateapp.template.view.ui.holder.CardHolder


class CardAdapter(list: Cards, context: Context, activity: Activity) :
    RecyclerView.Adapter<CardHolder>() {


    private var mCardList: ArrayList<String> = list.listCards
    private var mCardPosition: Int? = list.position
    private var mContext: Context = context
    private var mActivity: Activity = activity
    private var clickedRemove = false
    private val CLICKED_ITEM = 0
    private val NORMAL_ITEM = 1


    override fun getItemCount(): Int {
        return mCardList.count()
    }


    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.cardNumber.text = "****-****-****-${mCardList[position]}"
        holder.listCard.setOnClickListener {
            mCardPosition = position
            notifyDataSetChanged()
        }
        holder.cardDelete.setOnClickListener {
            if(!clickedRemove){
                clickedRemove = true
                mActivity.findViewById<View>(R.id.loading_credit_card).visibility = View.VISIBLE
                delay(position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val context = parent.context
        return when (viewType) {
            CLICKED_ITEM -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.card_line_clicked_view, parent, false)
                CardHolder(view)
            }
            NORMAL_ITEM -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_line_view, parent, false)
                CardHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        val clicked = mCardPosition
        return if (clicked == position) {
            CLICKED_ITEM
        } else {
            NORMAL_ITEM
        }

    }

    private fun deleteItem(position: Int) {
        mCardList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, mCardList.size)
    }

    private fun deleteCard(position: Int) {
        val alertDialog = Utils.showConfirmAlert(
            mActivity,
            mContext.resources.getString(R.string.prompt_alert),
            mContext.resources.getString(R.string.prompt_alert_delet_card)
        )
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
            .setOnClickListener { view2: View? ->
                clickedRemove = false
                alertDialog.dismiss() }
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
            .setTextColor(mActivity.getResources().getColor(R.color.bluePrimary))

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            .setTextColor(mActivity.getResources().getColor(R.color.redPrimary))

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            .setOnClickListener { view2: View? ->
                alertDialog.dismiss()
                clickedRemove = false
                mCardPosition = null
                mActivity.findViewById<View>(R.id.loading_credit_card).visibility = View.GONE
                deleteItem(position)
                if (mCardList.size == 0) {
                    mActivity.findViewById<View>(R.id.empty_content).visibility = View.VISIBLE
                    mActivity.findViewById<View>(R.id.full_content).visibility = View.GONE

                }
            }

    }

    fun delay(position: Int) {
        Handler().postDelayed(
            {
                deleteCard(position)
                mActivity.findViewById<View>(R.id.loading_credit_card).visibility = View.GONE
            },
            500
        )
    }
}