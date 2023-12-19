package com.jonathan.kawanuaapp.ui.scan

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jonathan.kawanuaapp.R
import com.jonathan.kawanuaapp.Result
import com.jonathan.kawanuaapp.ViewModelFactory
import com.jonathan.kawanuaapp.databinding.FragmentScanBinding
import com.jonathan.kawanuaapp.helper.getImageUri
import com.jonathan.kawanuaapp.helper.reduceFileImage
import com.jonathan.kawanuaapp.helper.uriToFile

class ScanFragment : Fragment() {

    private var _binding: FragmentScanBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var currentImageUri: Uri? = null

    private val viewModel by viewModels<ScanViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentScanBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnCamera.setOnClickListener {
            startCamera()
        }

        binding.btnGallery.setOnClickListener {
            startGallery()
        }

        binding.btnUpload.setOnClickListener {
            uploadImage()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.ivPlaceholder.setImageURI(it)
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
        }
    }

    private fun startCamera() {
        currentImageUri = getImageUri(requireActivity())
        launcherIntentCamera.launch(currentImageUri)
    }

    private fun uploadImage() {
        currentImageUri?.let { uri ->
            val imageFile = uriToFile(uri, requireContext()).reduceFileImage()
            Log.d("Image file", "showImage: ${imageFile.path}")
            showLoading(true)

            viewModel.uploadImage(imageFile).observe(requireActivity()) { result ->

                if (result != null) {
                    when (result) {
                        is Result.Loading -> {
                            showLoading(true)
                        }

                        is Result.Success -> {
                            result.data.status?.message?.let {
                                showToast(it)
                                Log.d("ScanFragment", "uploadImage: $it")
                            }
                            val data = result.data.data!!
                            val action = ScanFragmentDirections.actionScanToSpesies(data)
                            findNavController().navigate(action)

                            showLoading(false)
                        }
                        is Result.Error -> {
                            showToast(result.toString())
                            showLoading(false)
                        }
                    }
                }
            }
        } ?: showToast(getString(R.string.empty_image_warning))
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }
}