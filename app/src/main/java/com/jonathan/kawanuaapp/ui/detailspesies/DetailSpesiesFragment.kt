package com.jonathan.kawanuaapp.ui.detailspesies

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jonathan.kawanuaapp.R

class DetailSpesiesFragment : Fragment() {

    companion object {
        fun newInstance() = DetailSpesiesFragment()
    }

    private lateinit var viewModel: DetailSpesiesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_spesies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[DetailSpesiesViewModel::class.java]
        // TODO: Use the ViewModel
    }

}