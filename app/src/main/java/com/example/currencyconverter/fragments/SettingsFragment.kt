package com.example.currencyconverter.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import com.example.currencyconverter.App
import com.example.currencyconverter.IS_DARK_THEME
import com.example.currencyconverter.SHARED_PREFS
import com.example.currencyconverter.databinding.FragmentSettingsBinding
import com.example.currencyconverter.viewmodels.SettingsViewModel


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDarkThemeSwitcher()

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.darkThemeSwitcher.setOnCheckedChangeListener { _, isChecked ->
            (activity?.applicationContext as App).switchNightMode(isChecked)
        }
    }

    private fun setDarkThemeSwitcher(){
        val sharedPrefs = activity?.getSharedPreferences(SHARED_PREFS,0)
        val isDarkTheme = sharedPrefs?.getBoolean(IS_DARK_THEME,false)
        binding.darkThemeSwitcher.isChecked = isDarkTheme!!
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}