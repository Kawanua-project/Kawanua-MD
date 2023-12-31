package com.jonathan.kawanuaapp.ui.listnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jonathan.kawanuaapp.data.retrofit.response.ArticlesItem
import com.jonathan.kawanuaapp.R
import com.jonathan.kawanuaapp.helper.ViewModelFactory
import com.jonathan.kawanuaapp.databinding.FragmentListBeritaBinding
import com.jonathan.kawanuaapp.ui.adapter.NewsAdapter

class ListBeritaFragment : Fragment() {

    private var _binding: FragmentListBeritaBinding? = null
    private lateinit var news: List<ArticlesItem>
    private lateinit var adapter: NewsAdapter
    private lateinit var recyclerView: RecyclerView
    private val viewModel by viewModels<ListBeritaViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBeritaBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = requireActivity().findViewById<Toolbar>(R.id.toolbar)
        val appCompatActivity = requireActivity() as AppCompatActivity
        appCompatActivity.setSupportActionBar(toolbar)
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        viewModel.getNews()


        recyclerView = binding.rvNews

        viewModel.isLoading.observe(requireActivity()) {
            showLoading(it)
        }

        viewModel.news.observe(viewLifecycleOwner) { news ->
            this.news = news
            val viewType = NewsAdapter.VERTICAL
            adapter = NewsAdapter(news, viewType)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean) {
        _binding?.let { binding ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }
}