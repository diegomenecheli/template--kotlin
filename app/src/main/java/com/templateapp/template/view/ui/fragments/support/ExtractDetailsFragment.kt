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
import kotlinx.android.synthetic.main.fragment_extract_details.*


class ExtractDetailsFragment : Fragment(), View.OnClickListener {
    private var listener: OnFragmentInteractionListener? = null

    override fun onClick(view: View) {
        when (view.id) {
            R.id.list_dolar -> {
                ll_loading?.visibility = View.VISIBLE
                blockButtons()
                delay()
            }
            R.id.list_details -> {
                ll_loading?.visibility = View.VISIBLE
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
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.sup_details)
        val view = inflater.inflate(R.layout.fragment_extract_details, container, false)

        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        list_dolar.setOnClickListener(this)
        list_details.setOnClickListener(this)
        val args = arguments
        val price = args!!.getInt("price", 0)
        val name = args.getString("name", "")
        price_box.text = "R$$price,00"
        name_box.text = name
    }


    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }


    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        fun newInstance() = ExtractDetailsFragment()
    }

    fun delay() {
        Handler().postDelayed(
            {
                Utils.showToaster(
                    activity as AppCompatActivity,
                    resources.getString(R.string.toast_developing)
                )
                activity?.finish()
                ll_loading?.visibility = View.GONE
            },
            500
        )
    }

    fun blockButtons(){
        list_dolar.isEnabled = false
        list_dolar.isClickable = false
        list_details.isEnabled = false
        list_details.isClickable = false
    }

    fun enableButtons(){
        list_dolar.isEnabled = true
        list_dolar.isClickable = true
        list_details.isEnabled = true
        list_details.isClickable = true
    }

    override fun onResume() {
        super.onResume()
        enableButtons()
    }
}
