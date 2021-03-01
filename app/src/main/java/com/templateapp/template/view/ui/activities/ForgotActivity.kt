package com.templateapp.template.view.ui.activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.template_android.R
import com.templateapp.template.services.actions.Validation
import kotlinx.android.synthetic.main.activity_forgot.*

class ForgotActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_recovery -> {
                if ((Validation.isValidEmail(
                        this,
                        username!!.text.toString()
                    ))
                ) {
                    blockButtons()
                    loading?.visibility = View.VISIBLE
                    delay()
                }
            }
            R.id.btn_forgot -> {
                blockButtons()
                loading?.visibility = View.VISIBLE
                delay()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setDisplayShowHomeEnabled(true)

        btn_forgot.setOnClickListener(this)
        btn_recovery.setOnClickListener(this)
    }

    fun delay() {
        Handler().postDelayed(
            {
                finish()
                loading?.visibility = View.GONE
            },
            500
        )
    }

    override fun onResume() {
        super.onResume()
        enableButtons()
    }

    fun blockButtons(){
        btn_forgot.isEnabled = false
        btn_forgot.isClickable = false
        btn_recovery.isEnabled = false
        btn_recovery.isClickable = false
    }

    fun enableButtons(){
        btn_forgot.isEnabled = true
        btn_forgot.isClickable = true
        btn_recovery.isEnabled = true
        btn_recovery.isClickable = true
    }
}
