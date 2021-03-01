package com.templateapp.template.view.ui.fragments.support

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.template_android.R
import com.templateapp.template.services.actions.Validation
import com.templateapp.template.view.adapter.ChatAdapter
import kotlinx.android.synthetic.main.fragment_chat.*

class Messages(var listMessages : List<String>, var send : List<Boolean>)
private var mAdapter: ChatAdapter? = null
class ChatFragment : Fragment(), View.OnClickListener {

    private val listener: OnFragmentInteractionListener? = null
    var sendMessages: Messages = Messages(mutableListOf("olá como vai você?", "bem e você?", "ótimo também"), listOf(true, false, true))
    override fun onClick(view: View) {
        when (view.id) {
            R.id.send_message -> {
                if (Validation.isValidUserConversation(activity as AppCompatActivity, message!!.text.toString())) {
                    sendMessages.listMessages += message!!.text.toString()
                    sendMessages.send += true
                    setupRecycler(sendMessages)
                    message.setText("")
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        send_message.setOnClickListener(this)
        setupRecycler(sendMessages)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.sup_chat)
        val view = inflater.inflate(R.layout.fragment_chat, container, false)
        return view
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        fun newInstance() = ChatFragment()
    }

    private fun setupRecycler(list: Messages) {
        val layoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = layoutManager
        mAdapter = context?.let { ChatAdapter(list) }
        recyclerView.adapter = mAdapter
    }
}
