package com.templateapp.template.view.ui.activities.settings

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.template_android.R
import com.templateapp.template.view.ui.fragments.FinishFragment
import com.templateapp.template.view.ui.fragments.support.*
import java.util.*


open class SecondarySupportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary_support)

        var fragment: Fragment? = null
        this.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        when (Objects.requireNonNull<Any>(intent.getIntExtra("id", 0))) {
            R.id.ll_contact -> {
                fragment = ChatFragment()
            }
            R.id.ll_edit -> {
                fragment = FormFragment()
            }
            R.id.ll_faq -> {
                fragment = FAQFragment()
            }
            R.id.ll_finish -> {
                fragment = FinishFragment()
            }
            R.id.ll_ask -> {
                fragment = AskFragment()
            }
            R.id.ll_extract -> {
                fragment = ExtractFragment()
            }
            else -> {
            }
        }
        if(intent.getStringExtra("questions") != null){
            fragment = QuestionFragment()
            val bundle = Bundle()
            bundle.putString("questions", intent.getStringExtra("questions") )
            bundle.putString("answers", intent.getStringExtra("answers") )
            fragment.arguments = bundle
        }
        if(intent.getStringExtra("name") != null){
            fragment = ExtractDetailsFragment()
            val bundle = Bundle()
            bundle.putString("name", intent.getStringExtra("name") )
            bundle.putString("date", intent.getStringExtra("date") )
            bundle.putInt("price", intent.getIntExtra("price", 0) )
            fragment.arguments = bundle
        }
        //replacing the fragment
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.rootFrameSecondary, fragment)
                .commit()
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return false
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return false
    }
}


