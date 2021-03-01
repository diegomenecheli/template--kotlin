package com.templateapp.template.view.ui.fragments


import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.example.template_android.R
import com.templateapp.template.services.actions.Utils
import kotlinx.android.synthetic.main.fragment_finish.*

class FinishFragment : Fragment(), View.OnClickListener {

    private var listener: OnFragmentInteractionListener? = null

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_submit -> {
                loading?.visibility = View.VISIBLE
                blockButtons()
                delay()
            }
            R.id.btn_first -> {
                loading?.visibility = View.VISIBLE
                blockButtons()
                delay()
            }
            R.id.btn_second -> {
                loading?.visibility = View.VISIBLE
                blockButtons()
                delay()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.sup_finish)
        val view = inflater.inflate(R.layout.fragment_finish, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_submit.setOnClickListener(this)
        btn_first.setOnClickListener(this)
        btn_second.setOnClickListener(this)
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }


    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        fun newInstance() = FinishFragment()
    }

    fun delay() {
        Handler().postDelayed(
            {
                Utils.showToaster(
                    activity as AppCompatActivity,
                    resources.getString(R.string.toast_developing)

                )
                loading?.visibility = View.GONE
                activity?.finish()
            },
            500
        )
    }

    fun blockButtons(){
        btn_submit.isEnabled = false
        btn_submit.isClickable = false
        btn_first.isEnabled = false
        btn_first.isClickable = false
        btn_second.isEnabled = false
        btn_second.isClickable = false
    }

    fun enableButtons(){
        btn_first.isEnabled = true
        btn_first.isClickable = true
        btn_submit.isEnabled = true
        btn_submit.isClickable = true
        btn_second.isEnabled = true
        btn_second.isClickable = true
    }
}
