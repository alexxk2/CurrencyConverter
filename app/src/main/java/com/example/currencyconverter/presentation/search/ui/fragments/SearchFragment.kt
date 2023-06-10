package com.example.currencyconverter.presentation.search.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencyconverter.presentation.search.ui.adapters.CurrencyAdapter
import com.example.currencyconverter.databinding.FragmentSearchBinding
import com.example.currencyconverter.domain.models.CurrencyInfo
import com.example.currencyconverter.presentation.search.view_model.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private var isLeftClicked = true
    private val viewModel: SearchViewModel by viewModel()
    private lateinit var adapter: CurrencyAdapter


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

        with(binding){
            searchEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val filteredList = viewModel.getCurrencyFilteredList(s.toString())
                    adapter.updateDataSet(filteredList)
                }

                override fun afterTextChanged(s: Editable?) {
                    changeClearButtonVisibility(s)
                }
            })

            backButton.setOnClickListener { findNavController().navigateUp() }
            buttonClear.setOnClickListener { clearSearchInput() }
        }
    }

    private fun clearSearchInput() {
        binding.searchEditText.setText("")
    }

    private fun changeClearButtonVisibility(input: Editable?) {
        binding.buttonClear.visibility = if (input.isNullOrEmpty()) View.GONE
        else View.VISIBLE
    }

    private fun setRecyclerView() {
        adapter = CurrencyAdapter(viewModel.getCurrencyDefaultList(), requireContext(), object: CurrencyAdapter.CurrencyActionListener{
            override fun onClickCurrency(currencyInfo: CurrencyInfo) {
                val action = SearchFragmentDirections.actionSearchFragmentToConverterFragment()

                navigate(action)

                if (isLeftClicked) viewModel.putCurrencyInStorage(LEFT_CURRENCY, currencyInfo)
                else viewModel.putCurrencyInStorage(RIGHT_CURRENCY, currencyInfo)

            }

        })
        with(binding){
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.setHasFixedSize(true)
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