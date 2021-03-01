package com.templateapp.template.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.template_android.R
import com.templateapp.template.view.adapter.SearchAdapter
import kotlinx.android.synthetic.main.activity_search.*


class Results(var listCards: List<String>, var position: Int?)
class SearchActivity : AppCompatActivity() {

    private var mAdapter: SearchAdapter? = null
    lateinit var searchBox: EditText
    var listResults: Results = Results(mutableListOf("4444", "5555", "6666", "4444", "5555", "6666", "4444", "5555", "6666", "4444", "5555", "6666"), null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        searchBox = findViewById(R.id.search_box)
        searchBox.addTextChangedListener(textWatcher)

        setupRecycler(listResults)
    }

    private fun setupRecycler(list: Results) {
        val recycler = recycler_view
        val layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager
        mAdapter = SearchAdapter(list)
        recycler.adapter = mAdapter
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            if(count > 0){
                ll_results.visibility = View.VISIBLE
            }else {
                ll_results.visibility = View.GONE
                listResults.position = null
                setupRecycler(listResults)
            }
        }
    }
}
