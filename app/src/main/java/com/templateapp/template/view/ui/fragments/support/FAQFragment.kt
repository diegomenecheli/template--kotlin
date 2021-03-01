package com.templateapp.template.view.ui.fragments.support

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.template_android.R
import com.templateapp.template.view.adapter.QuestionAdapter
import kotlinx.android.synthetic.main.fragment_faq.view.*

private var mAdapter: QuestionAdapter? = null
class Questions(var listQuestions : List<String>, var listAnswers : List<String>)
class FAQFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.sup_faq)
        val view = inflater.inflate(R.layout.fragment_faq, container, false)
        val listQuestions: Questions = Questions(listOf(resources.getString(R.string.template_text_small), resources.getString(R.string.sup_ask), resources.getString(R.string.template_text_small)), listOf(resources.getString(R.string.template_text_long), resources.getString(R.string.template_texto_medio), resources.getString(R.string.template_text_small)))

        setupRecycler(view.recycler_view, listQuestions)
        // Inflate the layout for this fragment
        return view
    }

    private fun setupRecycler(recyclerView: RecyclerView, list: Questions) {
        val layoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = layoutManager
        mAdapter = context?.let { QuestionAdapter(list, it, activity as AppCompatActivity) }
        recyclerView.adapter = mAdapter
    }

    public fun changeFragment(){

    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        fun newInstance() = FAQFragment()
    }
}
