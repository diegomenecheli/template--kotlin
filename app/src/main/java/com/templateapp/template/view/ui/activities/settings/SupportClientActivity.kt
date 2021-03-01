package com.templateapp.template.view.ui.activities.settings

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.template_android.R
import kotlinx.android.synthetic.main.activity_support_client.*


class SupportClientActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_support_client)

        ll_faq.setOnClickListener(this)
        ll_edit.setOnClickListener(this)
        ll_contact.setOnClickListener(this)
        ll_finish.setOnClickListener(this)
        ll_ask.setOnClickListener(this)
        ll_extract.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.ll_contact -> {
                loading?.visibility = View.VISIBLE
//                transicion(view.id)
                blockButtons()
                delay(view.id)
            }
            R.id.ll_edit -> {
                loading?.visibility = View.VISIBLE
//                transicion(view.id)
                blockButtons()
                delay(view.id)
            }
            R.id.ll_faq -> {
                loading?.visibility = View.VISIBLE
//                transicion(view.id)
                blockButtons()
                delay(view.id)
            }
            R.id.ll_finish -> {
                loading?.visibility = View.VISIBLE
//                transicion(view.id)
                blockButtons()
                delay(view.id)
            }
            R.id.ll_ask -> {
                loading?.visibility = View.VISIBLE
//                transicion(view.id)
                blockButtons()
                delay(view.id)
            }
            R.id.ll_extract -> {
                loading?.visibility = View.VISIBLE
//                transicion(view.id)
                blockButtons()
                delay(view.id)
            }
        }
    }
    fun delay(id: Int) {
        Handler().postDelayed(
            {
                val fluxActivity = Intent(baseContext, SecondarySupportActivity::class.java)
                fluxActivity.putExtra("id", id)
                fluxActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(fluxActivity)
                loading?.visibility = View.GONE
            },
            500
        )
    }
    override fun onResume() {
        super.onResume()
        enableButtons()
    }

    fun transicion(id: Int){
        val intent = Intent(baseContext, SecondarySupportActivity::class.java)
        intent.putExtra("id", id)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    fun blockButtons(){
        ll_faq.isEnabled = false
        ll_faq.isClickable = false
        ll_edit.isEnabled = false
        ll_edit.isClickable = false
        ll_contact.isEnabled = false
        ll_contact.isClickable = false
        ll_finish.isEnabled = false
        ll_finish.isClickable = false
        ll_ask.isEnabled = false
        ll_ask.isClickable = false
        ll_extract.isEnabled = false
        ll_extract.isClickable = false
    }

    fun enableButtons(){
        ll_faq.isEnabled = true
        ll_faq.isClickable = true
        ll_edit.isEnabled = true
        ll_edit.isClickable = true
        ll_contact.isEnabled = true
        ll_contact.isClickable = true
        ll_finish.isEnabled = true
        ll_finish.isClickable = true
        ll_ask.isEnabled = true
        ll_ask.isClickable = true
        ll_extract.isEnabled = true
        ll_extract.isClickable = true
    }

}
