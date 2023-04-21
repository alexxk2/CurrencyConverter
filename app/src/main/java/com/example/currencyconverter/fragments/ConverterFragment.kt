package com.example.currencyconverter.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.currencyconverter.R
import com.example.currencyconverter.databinding.FragmentConverterBinding
import com.example.currencyconverter.models.CurrencyInfo
import com.example.currencyconverter.utils.EditTextUtils
import com.example.currencyconverter.viewmodels.ConverterViewModel
import com.google.gson.Gson
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols


const val SHARED_PREFS = "shared_prefs"

class ConverterFragment : Fragment() {
    private var _binding: FragmentConverterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ConverterViewModel by viewModels()

    private lateinit var leftCurrency : CurrencyInfo
    private lateinit var rightCurrency : CurrencyInfo


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

        _binding = FragmentConverterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getCurrencyDataFromSharedPrefs()
        setFlags()

        //замылил, чтобы запросы просто так не делались
        //viewModel.makeRequest(leftCurrency.code,rightCurrency.code)

        viewModel.isRateUpdated.observe(viewLifecycleOwner){isRateUpdated->
            if (isRateUpdated){
                binding.progressBar.isVisible = false
                binding.convertButton.isVisible = true
            }
        }

        viewModel.convertedValue.observe(viewLifecycleOwner){newConvertedValue ->
            val convertingValue = formatCurrencyDisplay(binding.startEditText.text.toString())
            binding.baseCurrencyTextView.text = getString(R.string.base_currency_text_format,convertingValue,leftCurrency.symbol)
            binding.resultTextView.text = getString(R.string.result_currency_text_format,formatCurrencyDisplay(newConvertedValue),rightCurrency.symbol)
        }

        viewModel.conversionCounter.observe(viewLifecycleOwner){conversionCounter->
            if (conversionCounter>=20){
                viewModel.makeRequest(leftCurrency.code,rightCurrency.code)
                viewModel.resetConversionCounter()
            }
        }

        binding.settingsButton.setOnClickListener {
            val action = ConverterFragmentDirections.actionConverterFragmentToSettingsFragment()
            navigate(action)
        }

        binding.leftCurrency.setOnClickListener {
            val action =
                ConverterFragmentDirections.actionConverterFragmentToSearchFragment(true)
            navigate(action)
        }

        binding.rightCurrency.setOnClickListener {
            val action =
                ConverterFragmentDirections.actionConverterFragmentToSearchFragment(false)
            navigate(action)
        }

        binding.startEditText.addDecimalLimiter()

        binding.swapCurrenciesButton.setOnClickListener {
            swapFlags()
            setFlags()
            viewModel.swapCurrencies()
        }

        binding.convertButton.setOnClickListener {
            convertWithGivenValue()
        }

        binding.startEditText.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when(actionId){
                EditorInfo.IME_ACTION_GO -> {
                    convertWithGivenValue()
                    true
                }
                else -> false
            }
        }

        binding.buttonClear.setOnClickListener { clearSearchInput() }
        manageHintMessage()
    }

    private fun changeClearButtonVisibility(input: Editable?) {
        binding.buttonClear.visibility = if (input.isNullOrEmpty()) View.GONE
        else View.VISIBLE
    }
    private fun clearSearchInput() {
        binding.startEditText.setText("")
        clearResults()
    }

    private fun manageHintMessage() {
        binding.startEditText.hint = getString(R.string.enter_a_value)
        binding.startEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) binding.startEditText.hint =
                    getString(R.string.enter_a_value)
                else binding.startEditText.hint = ""

                changeClearButtonVisibility(s)
            }
        })
    }

    private fun convertWithGivenValue(){
        val inputValue = binding.startEditText.text.toString()
        if (inputValue.isNotEmpty()) {
            viewModel.convert(inputValue.toDouble())
        }
        else {
            Toast.makeText(context, R.string.no_input_error, Toast.LENGTH_SHORT).show()
            clearResults()
        }
        hideKeyboard(binding.startEditText)
    }
    private fun clearResults(){
        binding.resultTextView.text = ""
        binding.baseCurrencyTextView.text = ""
    }

    private fun formatCurrencyDisplay(input: String): String{
        return if (input.isEmpty()) getString(R.string.last_input)
        else {
            val decimalFormat = DecimalFormat("###,###,##0.00")
            val decimalFormatSymbols = DecimalFormatSymbols()
            decimalFormatSymbols.groupingSeparator = ' '
            decimalFormat.decimalFormatSymbols = decimalFormatSymbols
            decimalFormat.format(input.toDouble())
        }
    }

    private fun navigate(action: NavDirections) {
        findNavController().navigate(action)
    }

    private fun getCurrencyDataFromSharedPrefs(){
        leftCurrency = getDataFromSharedPrefs(CurrencyInfo.DEFAULT_LEFT, LEFT_CURRENCY)
        rightCurrency = getDataFromSharedPrefs(CurrencyInfo.DEFAULT_RIGHT, RIGHT_CURRENCY)
    }

    private fun getDataFromSharedPrefs(defaultValue: CurrencyInfo, sharedPrefsName: String): CurrencyInfo {
        val sharedPrefs = activity?.getSharedPreferences(SHARED_PREFS,0)
        val defaultJsonLeft = Gson().toJson(defaultValue)
        val leftJsonCurrency = sharedPrefs?.getString(sharedPrefsName,defaultJsonLeft)
        return Gson().fromJson(leftJsonCurrency, CurrencyInfo::class.java)
    }

    private fun swapFlags(){
        putSharedPrefs(LEFT_CURRENCY,rightCurrency)
        putSharedPrefs(RIGHT_CURRENCY,leftCurrency)
        getCurrencyDataFromSharedPrefs()
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

    private fun setFlags(){
        binding.leftCurrencyImage.setImageResource(leftCurrency.flag)
        binding.rightCurrencyImage.setImageResource(rightCurrency.flag)
    }

    private fun EditText.addDecimalLimiter(maxLimit: Int = 2) {

        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val str = this@addDecimalLimiter.text!!.toString()
                if (str.isEmpty()) return
                val str2 = EditTextUtils.decimalLimiter(str, maxLimit)
                if (str2 != str) {
                    this@addDecimalLimiter.setText(str2)
                    val pos = this@addDecimalLimiter.text!!.length
                    this@addDecimalLimiter.setSelection(pos)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val BASE_URL = "https://api.currencyapi.com"
        const val API_KEY = "m8t10hvSal3gRP9IIlLERGTxd8CgbqcUHkoKKi1Q"
        const val LEFT_CURRENCY = "left_currency"
        const val RIGHT_CURRENCY = "right_currency"
    }

}