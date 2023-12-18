package com.jonathan.kawanuaapp.ui.detailnews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import com.jonathan.kawanuaapp.ArticlesItem
import com.jonathan.kawanuaapp.R
import com.jonathan.kawanuaapp.ViewModelFactory
import com.jonathan.kawanuaapp.databinding.FragmentDetailNewsBinding
import com.jonathan.kawanuaapp.loadImage

class DetailNewsFragment : Fragment() {

    private var _binding: FragmentDetailNewsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<DetailNewsViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailNewsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val news = arguments?.getParcelable<ArticlesItem>("news")
        val news = arguments?.getParcelable<ArticlesItem>("newsItem")

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
            // Use the selectedNews to update UI or perform actions
            binding.apply {
                judul.text = selectedNews.title
                tvDetailSpesies.text = selectedNews.content
                ivDetailNews.loadImage(selectedNews.urlToImage)
            }
        }
    }
}