package com.templateapp.template.view.ui.activities.Payments

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.template_android.R
import com.google.zxing.integration.android.IntentIntegrator

class QRCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode)
        initScan()
    }

    private fun initScan() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt("Alinhar com o QR Code")
        integrator.setOrientationLocked(false)
        integrator.setBarcodeImageEnabled(true)
//        integrator.setBeepEnabled(false)
        integrator.initiateScan()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if(result != null){
            if(result.contents == null){
                //the result data is null  or empty then
                Toast.makeText(this, "The data is empty", Toast.LENGTH_LONG).show()
                finish()
            } else {
                //the camera will not close if the result is still null
                Toast.makeText(this, result.contents.toString(), Toast.LENGTH_LONG).show()
                finish()
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
