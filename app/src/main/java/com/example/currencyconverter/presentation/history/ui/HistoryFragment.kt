package com.example.currencyconverter.presentation.history.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencyconverter.databinding.FragmentHistoryBinding
import com.example.currencyconverter.domain.models.HistoryInfo
import com.example.currencyconverter.presentation.history.models.ScreenState
import com.example.currencyconverter.presentation.history.view_model.HistoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var historyAdapter: HistoryAdapter
    private val viewModel: HistoryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        viewModel.getAllHistory()

        viewModel.historyData.observe(viewLifecycleOwner) { historyData ->
            historyAdapter.submitList(historyData)
        }

        viewModel.screenState.observe(viewLifecycleOwner) { screenState ->
            manageScreen(screenState)
        }

    }

    fun manageScreen(screenState: ScreenState) {
        when (screenState) {
            ScreenState.Content -> {
                with(binding) {
                    historyRecyclerView.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    errorLayout.visibility = View.GONE
                    noContentLayout.visibility = View.GONE
                }
            }

            ScreenState.Empty -> {
                with(binding) {
                    historyRecyclerView.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    errorLayout.visibility = View.GONE
                    noContentLayout.visibility = View.VISIBLE
                }
            }

            ScreenState.Error -> {
                with(binding) {
                    historyRecyclerView.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    errorLayout.visibility = View.VISIBLE
                    noContentLayout.visibility = View.GONE
                }
            }

            ScreenState.Loading -> {
                with(binding) {
                    historyRecyclerView.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                    errorLayout.visibility = View.GONE
                    noContentLayout.visibility = View.GONE
                }
            }
        }
    }

    private fun setRecyclerView() {

        historyAdapter =
            HistoryAdapter(requireContext(), object : HistoryAdapter.HistoryActionListener {
                override fun onClickItem(historyInfo: HistoryInfo) {
                }
            })
        binding.historyRecyclerView.adapter = historyAdapter
        binding.historyRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.historyRecyclerView.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}