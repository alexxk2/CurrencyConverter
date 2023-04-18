package com.example.currencyconverter.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.currencyconverter.R
import com.example.currencyconverter.databinding.FragmentConverterBinding
import com.example.currencyconverter.models.CurrencyInfo
import com.example.currencyconverter.sources.ConverterApi
import com.example.currencyconverter.utils.EditTextUtils
import com.example.currencyconverter.viewmodels.ConverterViewModel
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DecimalFormat
import java.util.*


const val SHARED_PREFS = "shared_prefs"

class ConverterFragment : Fragment() {
    private var _binding: FragmentConverterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ConverterViewModel by viewModels()


    //transfer later to viewModel
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

        viewModel.convertedValue.observe(viewLifecycleOwner){newConvertedValue ->
            binding.testTextView.text = getString(R.string.currency_input_format,inputCurrencyFormat(newConvertedValue))
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
            viewModel.swapCurrencies(leftCurrency.code,rightCurrency.code)
        }

        binding.convertButton.setOnClickListener {
            val inputValue = binding.startEditText.text.toString()
            if (inputValue.isNotEmpty()) {
                viewModel.updateExchangeRate(leftCurrency.code,rightCurrency.code)
                viewModel.convert(inputValue.toFloat())
            }
            else Toast.makeText(context,R.string.no_input_error,Toast.LENGTH_SHORT).show()
        }
    }


    //use when show result with a getString "%1$s $"
    private fun inputCurrencyFormat(input: String): String{
        val decimalFormat = DecimalFormat("###,###,##0.00")
        return decimalFormat.format(input.toDouble())
    }

    private fun navigate(action: NavDirections) {
        findNavController().navigate(action)
    }

    private fun getCurrencyDataFromSharedPrefs(){
        leftCurrency = sharedPrefsRequest(CurrencyInfo.DEFAULT_LEFT, LEFT_CURRENCY)
        rightCurrency = sharedPrefsRequest(CurrencyInfo.DEFAULT_RIGHT, RIGHT_CURRENCY)
    }

    private fun sharedPrefsRequest(defaultValue: CurrencyInfo, sharedPrefsName: String): CurrencyInfo {
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