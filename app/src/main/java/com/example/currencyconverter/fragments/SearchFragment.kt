package com.example.currencyconverter.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencyconverter.SHARED_PREFS
import com.example.currencyconverter.adapters.CurrencyAdapter
import com.example.currencyconverter.databinding.FragmentSearchBinding
import com.example.currencyconverter.models.CurrencyInfo
import com.example.currencyconverter.models.SearchService
import com.example.currencyconverter.viewmodels.SearchViewModel
import com.google.gson.Gson

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()
    private val searchService = SearchService()
    private lateinit var adapter: CurrencyAdapter
    private var isLeftClicked = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            isLeftClicked = it.getBoolean(IS_LEFT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        setRecyclerView()

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val filteredList = searchService.searchFilter(s.toString())
                adapter.updateDataSet(filteredList)
            }

            override fun afterTextChanged(s: Editable?) {
                changeClearButtonVisibility(s)
            }
        })

        binding.backButton.setOnClickListener { findNavController().navigateUp() }
        binding.buttonClear.setOnClickListener { clearSearchInput() }


    }

    private fun clearSearchInput() {
        binding.searchEditText.setText("")
    }

    private fun changeClearButtonVisibility(input: Editable?) {
        binding.buttonClear.visibility = if (input.isNullOrEmpty()) View.GONE
        else View.VISIBLE
    }

    private fun setRecyclerView() {
        adapter = CurrencyAdapter(searchService.listOfCurrencies, requireContext(), object: CurrencyAdapter.CurrencyActionListener{
            override fun onClickCurrency(currencyInfo: CurrencyInfo) {
                val action = SearchFragmentDirections.actionSearchFragmentToConverterFragment()
                navigate(action)

                if (isLeftClicked) putSharedPrefs(LEFT_CURRENCY, currencyInfo)
                else putSharedPrefs(RIGHT_CURRENCY, currencyInfo)

            }

        })
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
    }

    private fun putSharedPrefs(side: String, currencyInfo: CurrencyInfo){
        val sharedPrefs = activity?.getSharedPreferences(SHARED_PREFS,0)
        val jSonCurrency = Gson().toJson(currencyInfo, CurrencyInfo::class.java)
        sharedPrefs?.let {
            it.edit()
                .putString(side, jSonCurrency)
                .apply()
        }

    }

    private fun navigate(action: NavDirections){
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val IS_LEFT = "isLeftClicked"
        const val LEFT_CURRENCY = "left_currency"
        const val RIGHT_CURRENCY = "right_currency"
    }

}