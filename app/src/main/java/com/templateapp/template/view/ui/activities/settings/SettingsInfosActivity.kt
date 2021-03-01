package com.templateapp.template.view.ui.activities.settings



import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.template_android.R
import com.templateapp.template.services.actions.Mask
import com.templateapp.template.services.actions.Validation
import kotlinx.android.synthetic.main.activity_settings_infos.*

class SettingsInfosActivity : AppCompatActivity(), View.OnClickListener {



    override fun onClick(view: View) {
        when (view.id) {
            R.id.ll_picture -> {
                showImagePickerOptions(this)
            }
            R.id.btn_submit -> {
                if (Validation.isValidUserName(this, name!!.text.toString()) &&
                    Validation.isValidPhoneNumber(
                        this,
                        phone!!.text.toString()
                    ) &&
                    Validation.isValidEmail(this, username!!.text.toString()) &&
//                    Validation.isValidAddress(this, address!!.text.toString()) &&
                    Validation.isValidCPF(this, cpf!!.text.toString())
                ) {
                    finish()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_infos)

        mask()
        ll_picture.setOnClickListener(this)
        btn_submit.setOnClickListener(this)


    }

    private fun mask() {
        cpf.addTextChangedListener(Mask.mask("###.###.###-##", cpf))
        phone.addTextChangedListener(Mask.mask("(##) #####-####", phone))
    }

    fun selectImageInAlbum() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM)
        }
    }

    fun takePhoto() {
        val intent1 = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent1.resolveActivity(packageManager) != null) {
            startActivityForResult(intent1, REQUEST_TAKE_PHOTO)
        }
    }

    fun showImagePickerOptions(context: Context?) {
        // setup the alert builder
        val builder =
            AlertDialog.Builder(context!!)
        builder.setTitle("Escolher a foto do Perfil")

        // add a list
        val animals =
            arrayOf("Tirar foto", "Escolher foto da galeria")
        builder.setItems(
            animals
        ) { dialog: DialogInterface?, which: Int ->
            when (which) {
                0 -> takePhoto()
                1 -> selectImageInAlbum()
            }
        }

        // create and show the alert dialog
        val dialog = builder.create()
        dialog.show()
    }


    companion object {
        private val REQUEST_TAKE_PHOTO = 0
        private val REQUEST_SELECT_IMAGE_IN_ALBUM = 1
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0){
            var bmp = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(bmp)
        }else if (requestCode == 1){
            imageView.setImageURI(data?.data)

        }
    }

}
