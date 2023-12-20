package com.jonathan.kawanuaapp.ui.detailspesies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import com.jonathan.kawanuaapp.R
import com.jonathan.kawanuaapp.data.retrofit.response.Data
import com.jonathan.kawanuaapp.databinding.FragmentDetailSpesiesBinding
import com.jonathan.kawanuaapp.helper.ViewModelFactory
import com.jonathan.kawanuaapp.helper.loadImage

class DetailSpesiesFragment : Fragment() {

    private var _binding: FragmentDetailSpesiesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<DetailSpesiesViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailSpesiesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val news = arguments?.getParcelable<Data>("data")

        if (news != null) {
            viewModel.setSelectedNews(news)
        }

        val toolbar = requireActivity().findViewById<Toolbar>(R.id.toolbar)
        val appCompatActivity = requireActivity() as AppCompatActivity
        appCompatActivity.setSupportActionBar(toolbar)
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        viewModel.selectedNews.observe(viewLifecycleOwner) { selectedNews ->
            binding.apply {
                namaSpesies.text = selectedNews.endangeredPrediction
                tvDetailSpesies.text = selectedNews.result?.deskripsi
                ivDetailSpesies.loadImage(selectedNews.imageUrl)
            }
        }
    }
}