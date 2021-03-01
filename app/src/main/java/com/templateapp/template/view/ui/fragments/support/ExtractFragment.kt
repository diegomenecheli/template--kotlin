package com.templateapp.template.view.ui.fragments.support

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.template_android.R
import com.templateapp.template.view.adapter.ExtractAdapter
import kotlinx.android.synthetic.main.fragment_extract.view.*

private var mAdapter: ExtractAdapter? = null
class Extract(var nameDetails : List<String>, var data : List<String>, var price: List<Int>)
class ExtractFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.sup_extract)
        val view = inflater.inflate(R.layout.fragment_faq, container, false)
        val listExtract: Extract = Extract(listOf(resources.getString(R.string.team), resources.getString(R.string.team), resources.getString(R.string.team)), listOf("10/10/2010", "11/11/2011", "12/12/2012"), listOf(2500, 2000, 1787))

        setupRecycler(view.recycler_view, listExtract)
        // Inflate the layout for this fragment
        return view
    }

    private fun setupRecycler(recyclerView: RecyclerView, list: Extract) {
        val layoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = layoutManager
        mAdapter = context?.let { ExtractAdapter(list, it, activity as AppCompatActivity) }
        recyclerView.adapter = mAdapter
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        fun newInstance() = ExtractFragment()
    }
}
