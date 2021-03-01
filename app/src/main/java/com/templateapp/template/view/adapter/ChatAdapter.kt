package com.templateapp.template.view.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template_android.R
import com.templateapp.template.view.ui.fragments.support.Messages
import com.templateapp.template.view.ui.holder.ChatHolder

class ChatAdapter(list: Messages) : RecyclerView.Adapter<ChatHolder>() {

    private var mCardList: List<String> = list.listMessages
    private var mSend: List<Boolean> = list.send
    private val SEND_ITEM = 0
    private val RECEIVE_ITEM = 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        val context = parent.context
        return when (viewType) {
            SEND_ITEM -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_message_sent, parent, false)
                ChatHolder(view)
            }
            RECEIVE_ITEM -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message_received, parent, false)
                ChatHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return mCardList.count()
    }


    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        holder.textBody.text = mCardList[position]
//        holder.textTime
    }


    override fun getItemViewType(position: Int): Int {
        val send: Boolean = mSend[position]
        return if(send){
            SEND_ITEM
        } else{
            RECEIVE_ITEM
        }

    }

    public fun updateList(){
        notifyDataSetChanged()
    }


}