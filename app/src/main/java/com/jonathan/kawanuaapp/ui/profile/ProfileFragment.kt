package com.jonathan.kawanuaapp.ui.profile

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jonathan.kawanuaapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val switchTheme = binding.switchTheme

        binding.buttonLanguage.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}