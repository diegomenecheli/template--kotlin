package com.templateapp.template.view.ui.fragments.support

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.example.template_android.R
import com.templateapp.template.services.actions.Mask
import com.templateapp.template.services.actions.Utils
import com.templateapp.template.services.actions.Validation
import kotlinx.android.synthetic.main.fragment_form.*

class FormFragment : Fragment(), View.OnClickListener {

    private var listener: OnFragmentInteractionListener? = null

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_submit -> {
                if (Validation.isValidUserName(activity as AppCompatActivity, name!!.text.toString()) &&
                    Validation.isValidEmail(activity as AppCompatActivity, username!!.text.toString()) &&
//                    Validation.isValidAddress(this, address!!.text.toString()) &&
                    Validation.isValidCep(activity as AppCompatActivity, cep!!.text.toString())
                ) {
                    Utils.showToaster(
                        activity as AppCompatActivity,
                        resources.getString(R.string.toast_developing)
                    )
                    activity?.finish()
                }
            }
            R.id.btn_attachment -> {
                Utils.showToaster(
                    activity as AppCompatActivity,
                    resources.getString(R.string.toast_developing)
                )
                activity?.finish()
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
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.sup_forms)
        val view = inflater.inflate(R.layout.fragment_form, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_submit.setOnClickListener(this)
        btn_attachment.setOnClickListener(this)
        mask()

    }

    fun mask() {
        cep.addTextChangedListener(Mask.mask("#####-###", cep))
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }


    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        fun newInstance() = FAQFragment()
    }
}
