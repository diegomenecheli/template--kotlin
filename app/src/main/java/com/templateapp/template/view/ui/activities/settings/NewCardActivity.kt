package com.templateapp.template.view.ui.activities.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.template_android.R
import com.templateapp.template.services.actions.Mask
import com.templateapp.template.services.actions.Utils
import com.templateapp.template.services.actions.Validation
import kotlinx.android.synthetic.main.activity_new_card.*

class NewCardActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(view: View) {
        when (view.id) {

            R.id.btn_submit -> {
                if (Validation.isValidUserName(this, full_name!!.text.toString()) &&
                    Validation.isValidCardNumber(this, card_number!!.text.toString()) &&
                    Validation.isValidCVV(this, cvv!!.text.toString()) &&
                    Validation.isValidValidity(this, (expirationDate!!.text.toString()).filter { it.isDigit()})
                ) {
                    blockButtons()
                    loading.visibility = View.VISIBLE
                    delay()
                    Utils.hideSoftKeyboard(this)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_card)

        mask()
        btn_submit.setOnClickListener(this)
    }

    private fun mask() {
        card_number.addTextChangedListener(Mask.mask("####-####-####-####", card_number))
        expirationDate.addTextChangedListener(Mask.mask("##/##", expirationDate))
        cvv.addTextChangedListener(Mask.mask("###", cvv))
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
        btn_submit.isEnabled = false
        btn_submit.isClickable = false
    }

    fun enableButtons(){
        btn_submit.isEnabled = true
        btn_submit.isClickable = true
    }
}
