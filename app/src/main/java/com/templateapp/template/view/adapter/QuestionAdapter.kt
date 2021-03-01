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
import com.templateapp.template.view.ui.activities.settings.SecondarySupportActivity
import com.templateapp.template.view.ui.fragments.support.Questions
import com.templateapp.template.view.ui.holder.QuestionHolder

class QuestionAdapter(list: Questions, context: Context, activity: Activity) :
    RecyclerView.Adapter<QuestionHolder>() {

    private var mQuestionList: List<String> = list.listQuestions
    private var mAnswersList: List<String> = list.listAnswers
    private var mActivity: Activity = activity
    private var mContext: Context = context
    private val CLICKED_ITEM = 0
    private val NORMAL_ITEM = 1
    private var clicked = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionHolder {
        val context = parent.context
        return when (viewType) {
            CLICKED_ITEM -> {
                val view = LayoutInflater.from(context).inflate(R.layout.list_faq_clicked_view, parent, false)
                QuestionHolder(view)
            }
            NORMAL_ITEM -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.list_faq_view, parent, false)
                QuestionHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return mQuestionList.count()
    }


    override fun onBindViewHolder(holder: QuestionHolder, position: Int) {
        holder.questionResume.text = mQuestionList[position]
        holder.listItem.setOnClickListener {
            if (!clicked) {
                mActivity.findViewById<View>(R.id.loading_faq).visibility = View.VISIBLE
                clicked = true
                notifyDataSetChanged()
                delay(position)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(clicked){
            CLICKED_ITEM
        } else{
            NORMAL_ITEM
        }

    }




    fun updateQuestions(list: List<String>) {
        mQuestionList = list
    }

    fun delay(position: Int) {
        Handler().postDelayed(
            {
                val supportClient = Intent(mActivity, SecondarySupportActivity::class.java)
                supportClient.putExtra("questions", mQuestionList[position])
                supportClient.putExtra("answers", mAnswersList[position])
                mContext.startActivity(supportClient)
                mActivity.findViewById<View>(R.id.loading_faq).visibility = View.GONE
                clicked = false
                notifyDataSetChanged()
            },
            500
        )
    }

}