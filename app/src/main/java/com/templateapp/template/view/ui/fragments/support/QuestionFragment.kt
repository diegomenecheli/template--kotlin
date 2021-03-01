package com.templateapp.template.view.ui.fragments.support

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.example.template_android.R
import kotlinx.android.synthetic.main.fragment_question.*


class QuestionFragment : Fragment() {

    private var listener: FAQFragment.OnFragmentInteractionListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.sup_faq)
        val view = inflater.inflate(R.layout.fragment_question, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var args = arguments
        var question = args!!.getString("questions", "")
        var answers = args!!.getString("answers", "")
        title_text.text = question
        answer.text = answers
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }


    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        fun newInstance() = QuestionFragment()
    }
}
