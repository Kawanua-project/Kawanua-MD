package com.jonathan.kawanuaapp.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jonathan.kawanuaapp.ArticlesItem
import com.jonathan.kawanuaapp.ViewModelFactory
import com.jonathan.kawanuaapp.databinding.FragmentHomeBinding
import com.jonathan.kawanuaapp.ui.adapter.NewsAdapter
import com.jonathan.kawanuaapp.data.*
import com.jonathan.kawanuaapp.data.model.Zoo

class HomeFragment : Fragment(), NewsAdapter.NewsItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private var mMapView: MapView? = null
    private lateinit var news: List<ArticlesItem>
    private lateinit var adapter: NewsAdapter
    private lateinit var recyclerView: RecyclerView
    private val viewModel by viewModels<HomeViewModel> {
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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val more = binding.tvLihatSemua

        more.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeToContact()
            findNavController().navigate(action)
        }

        viewModel.getNews()

        recyclerView = binding.rvMain

        viewModel.news.observe(viewLifecycleOwner) { news ->
            this.news = news
            val viewType = NewsAdapter.HORIZONTAL
            adapter = NewsAdapter(news, viewType, this)
            recyclerView.adapter = adapter
            val horizontalLayoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false
            )
            recyclerView.layoutManager = horizontalLayoutManager
        }

//        mMapView = binding.mapView
//        mMapView.onCreate(savedInstanceState)

        mMapView = binding.mapView
        mMapView?.onCreate(savedInstanceState)

        mMapView?.getMapAsync { googleMap ->
            googleMap.uiSettings.isZoomControlsEnabled = true
            googleMap.uiSettings.isIndoorLevelPickerEnabled = true
            googleMap.uiSettings.isCompassEnabled = true
            googleMap.uiSettings.isMapToolbarEnabled = true

            getMyLocation()
            val manado = LatLng(1.4748, 124.8421)
            googleMap.addMarker(MarkerOptions().position(manado).title("Marker in Manado"))

            val zoomLevel = 10f
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(manado, zoomLevel))

            addManyMarker(googleMap)

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mMapView?.onDestroy()
        mMapView = null
    }

    override fun onResume() {
        super.onResume()
        mMapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView?.onLowMemory()
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                getMyLocation()
            }
        }

    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun getMyLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Check if the GoogleMap instance is available
            mMapView?.getMapAsync { googleMap ->
                googleMap.isMyLocationEnabled = true
            }
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun addManyMarker(googleMap: GoogleMap) {
        val zoo = listOf(
            Zoo("Cagar Alam Tangkoko", 1.4405406429500294, 125.12159875114887),
            Zoo("Taman Marga Satwa Tandurusa", 1.4555899195071487, 125.21203629083048),
            Zoo("Hutan Lindung Gunung Mahawu", 1.3555489312685314, 124.87102068528095),
            Zoo("Taman laut Bunaken", 1.6937077420656559, 124.75975512847128)
        )
        zoo.forEach {
            val latLng = LatLng(it.latitude, it.longitude)
            googleMap.addMarker(MarkerOptions().position(latLng).title(it.name))
        }
    }

    override fun onNewsItemClicked(newsItem: ArticlesItem) {
        val action = HomeFragmentDirections.actionHomeToDetail(newsItem)
        findNavController().navigate(action)
    }
}