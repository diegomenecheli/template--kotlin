package com.templateapp.template.view.ui.activities



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.template_android.R
import com.templateapp.template.services.actions.Mask
import com.templateapp.template.services.actions.Validation
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.btn_signup
import kotlinx.android.synthetic.main.activity_sign_up.btn_submit
import kotlinx.android.synthetic.main.activity_sign_up.cpf
import kotlinx.android.synthetic.main.activity_sign_up.name
import kotlinx.android.synthetic.main.activity_sign_up.phone
import kotlinx.android.synthetic.main.activity_sign_up.username

class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_submit -> {
                if (Validation.isValidUserName(this, name!!.text.toString()) &&
                        Validation.isValidCPF(this, cpf!!.text.toString()) &&
                        Validation.isValidEmail(this, username!!.text.toString()) &&
                        Validation.isValidBirthday(this, birthday!!.text.toString()) &&
                        Validation.isValidPhoneNumber(this, phone!!.text.toString()) &&
                        Validation.isValidPassword(this, password!!.text.toString()) &&
                        Validation.isValidPasswordConfirmation(this, password_confirmation!!.text.toString(), password!!.text.toString())
                    ) {
                    blockButtons()
                    loading?.visibility = View.VISIBLE
                    delay()
                }
            }
            R.id.btn_signup -> {
                blockButtons()
                loading?.visibility = View.VISIBLE
                delay()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        enableButtons()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        mask()

        btn_submit.setOnClickListener(this)
        btn_signup.setOnClickListener(this)
    }

    fun mask() {
        birthday.addTextChangedListener(Mask.mask("##/##/####", birthday))
        cpf.addTextChangedListener(Mask.mask("###.###.###-##", cpf))
        phone.addTextChangedListener(Mask.mask("(##) #####-####", phone))
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

    fun blockButtons(){
        btn_submit.isEnabled = false
        btn_submit.isClickable = false
        btn_signup.isEnabled = false
        btn_signup.isClickable = false
    }

    fun enableButtons(){
        btn_signup.isEnabled = true
        btn_signup.isClickable = true
        btn_submit.isEnabled = true
        btn_submit.isClickable = true
    }

}
