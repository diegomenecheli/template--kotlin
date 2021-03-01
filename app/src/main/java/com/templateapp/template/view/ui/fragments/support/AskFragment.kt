package com.templateapp.template.view.ui.fragments.support


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
import com.templateapp.template.services.actions.Validation
import kotlinx.android.synthetic.main.fragment_ask.*

class AskFragment : Fragment(), View.OnClickListener {

    private var listener: OnFragmentInteractionListener? = null

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_submit -> {
                if (Validation.isValidUserName(
                        activity as AppCompatActivity,
                        name!!.text.toString()
                    ) &&
                    Validation.isValidEmail(
                        activity as AppCompatActivity,
                        username!!.text.toString()
                    )
                ) {
                    loading.visibility = View.VISIBLE
                    blockButtons()
                    delay()
                }
            }
            R.id.btn_attachment -> {
                loading.visibility = View.VISIBLE
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
        val view = inflater.inflate(R.layout.fragment_ask, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.sup_ask)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_submit.setOnClickListener(this)
        btn_attachment.setOnClickListener(this)
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        fun newInstance() = AskFragment()
    }

    fun delay() {
        Handler().postDelayed(
            {
                Utils.showToaster(
                    activity as AppCompatActivity,
                    resources.getString(R.string.toast_developing)
                )
                activity?.finish()
                loading?.visibility = View.GONE
            },
            500
        )
    }

    fun blockButtons() {
        btn_submit.isEnabled = false
        btn_submit.isClickable = false
        btn_attachment.isEnabled = false
        btn_attachment.isClickable = false
    }

    fun enableButtons() {
        btn_attachment.isEnabled = true
        btn_attachment.isClickable = true
        btn_submit.isEnabled = true
        btn_submit.isClickable = true
    }

    override fun onResume() {
        super.onResume()
        enableButtons()
    }

}
