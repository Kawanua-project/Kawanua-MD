package com.jonathan.kawanuaapp.ui.profile

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jonathan.kawanuaapp.helper.ViewModelFactory
import com.jonathan.kawanuaapp.data.pref.UserPreference
import com.jonathan.kawanuaapp.data.pref.dataStore
import com.jonathan.kawanuaapp.databinding.FragmentProfileBinding
import com.jonathan.kawanuaapp.ui.login.LoginActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private lateinit var pref: UserPreference
    private val viewModel by viewModels<ProfileViewModel> {
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
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireActivity().application
        pref = UserPreference.getInstance(application.dataStore)

        val root: View = binding.root
        val switchTheme = binding.switchTheme

        binding.buttonLanguage.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }

        viewModel.getThemeSettings().observe(viewLifecycleOwner) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked = false
            }
        }

        binding.logout.setOnClickListener {
            viewModel.logout()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }

        switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            viewModel.saveThemeSetting(isChecked)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}