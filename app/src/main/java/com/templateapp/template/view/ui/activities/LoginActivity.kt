package com.templateapp.template.view.ui.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.template_android.R
import com.templateapp.template.services.actions.Validation
import com.templateapp.template.view.ui.activities.MainFlux.MainActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_submit.setOnClickListener(this)
        btn_forgot.setOnClickListener(this)
        btn_signup.setOnClickListener(this)

    }

    override fun onResume() {
        super.onResume()
        enableButtons()
    }

    override fun onClick(view: View) {

        when (view.id) {
            R.id.btn_submit -> {
                if ((Validation.isValidEmail(
                        this,
                        username!!.text.toString()
                    ) && Validation.isValidPassword(this, password!!.getText().toString())
                            )
                ) {
                    blockButtons()
                    loading?.visibility = View.VISIBLE
                    delay(MainActivity()) //delay to show loading
//                    val MainActivity = Intent(applicationContext, MainActivity::class.java)
//                    startActivity(MainActivity)
//                    loading?.visibility = View.GONE
                }
            }
            R.id.btn_signup -> {
                blockButtons()
                loading?.visibility = View.VISIBLE
                delay(SignUpActivity()) //delay to show loading
//                val signupActivity = Intent(applicationContext, SignUpActivity::class.java)
//                startActivity(signupActivity)
//                loading?.visibility = View.GONE
            }
            R.id.btn_forgot -> {
                blockButtons()
                loading?.visibility = View.VISIBLE
                delay(ForgotActivity()) //delay to show loading
//                val forgotActivity = Intent(applicationContext, ForgotActivity::class.java)
//                startActivity(forgotActivity)
//                loading?.visibility = View.GONE
            }
        }
    }

    fun delay(activity: Activity) {
        Handler().postDelayed(
            {
                val fluxActivity = Intent(applicationContext, activity::class.java)
                startActivity(fluxActivity)
                loading?.visibility = View.GONE
            },
            500
        )
    }

    fun blockButtons(){
        btn_forgot.isEnabled = false
        btn_forgot.isClickable = false
        btn_signup.isEnabled = false
        btn_signup.isClickable = false
        btn_submit.isEnabled = false
        btn_submit.isClickable = false
    }

    fun enableButtons(){
        btn_forgot.isEnabled = true
        btn_forgot.isClickable = true
        btn_signup.isEnabled = true
        btn_signup.isClickable = true
        btn_submit.isEnabled = true
        btn_submit.isClickable = true
    }
}

