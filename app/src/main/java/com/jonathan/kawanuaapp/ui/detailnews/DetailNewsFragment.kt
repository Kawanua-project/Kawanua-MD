package com.jonathan.kawanuaapp.ui.detailnews

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jonathan.kawanuaapp.R

class DetailNewsFragment : Fragment() {

    companion object {
        fun newInstance() = DetailNewsFragment()
    }

    private lateinit var viewModel: DetailNewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_news, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailNewsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}