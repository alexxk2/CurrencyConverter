package com.example.currencyconverter.presentation.settings.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.currencyconverter.App
import com.example.currencyconverter.IS_DARK_THEME
import com.example.currencyconverter.LANGUAGE
import com.example.currencyconverter.SHARED_PREFS
import com.example.currencyconverter.databinding.FragmentSettingsBinding
import com.example.currencyconverter.presentation.settings.view_model.SettingsViewModel

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SettingsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
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
        setLanguageChip()

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.darkThemeSwitcher.setOnCheckedChangeListener { _, isChecked ->
            (activity?.applicationContext as App).switchNightMode(isChecked)
        }

        binding.chipEnglish.setOnClickListener {
            (activity?.applicationContext as App).switchLanguage("en")
        }
        binding.chipRussian.setOnClickListener {
            (activity?.applicationContext as App).switchLanguage("ru")
        }
    }

    private fun setDarkThemeSwitcher(){
        val sharedPrefs = activity?.getSharedPreferences(SHARED_PREFS,0)
        val isDarkTheme = sharedPrefs?.getBoolean(IS_DARK_THEME,false)
        binding.darkThemeSwitcher.isChecked = isDarkTheme!!
    }

    private fun setLanguageChip(){
        val sharedPrefs = activity?.getSharedPreferences(SHARED_PREFS,0)
        val currentLanguage = sharedPrefs?.getString(LANGUAGE,"en")
        when(currentLanguage){
            "en" ->{
                binding.chipRussian.isChecked = false
                binding.chipEnglish.isChecked = true
            }
            else -> {
                binding.chipRussian.isChecked = true
                binding.chipEnglish.isChecked = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}