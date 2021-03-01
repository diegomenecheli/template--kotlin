package com.templateapp.template.view.ui.activities.Payments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.template_android.R
import com.templateapp.template.view.adapter.CardAdapter
import com.templateapp.template.view.ui.activities.settings.NewCardActivity
import kotlinx.android.synthetic.main.activity_credit_card.*


class Cards(var listCards : ArrayList<String>, var position : Int?)
class CreditCardActivity : AppCompatActivity(), View.OnClickListener {

    private var mAdapter: CardAdapter? = null
    var listCards: Cards = Cards(arrayListOf("4444", "5555", "6666"), null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit_card)
        setupRecycler(listCards)

        btn_submit.setOnClickListener(this)
    }

    private fun setupRecycler(list: Cards) {
        val recycler = recycler_view
        val layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager
        mAdapter = CardAdapter(list, this, this)
        recycler.adapter = mAdapter
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_submit -> {
                loading_credit_card?.visibility = View.VISIBLE
                blockButtons()
                delay()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        enableButtons()
    }

    fun delay() {
        Handler().postDelayed(
            {
                val addNewCard = Intent(applicationContext, NewCardActivity::class.java)
                startActivity(addNewCard)
                loading_credit_card?.visibility = View.GONE
            },
            500
        )
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

