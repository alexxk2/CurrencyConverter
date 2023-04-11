package com.example.currencyconverter.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.currencyconverter.databinding.FragmentConverterBinding
import com.example.currencyconverter.sources.ConverterApi
import com.example.currencyconverter.sources.SymbolsResponse
import com.example.currencyconverter.viewmodels.ConverterViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ConverterFragment : Fragment() {
    private var _binding: FragmentConverterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ConverterViewModel by viewModels()





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

        _binding = FragmentConverterBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.settingsButton.setOnClickListener {
            val action = ConverterFragmentDirections.actionConverterFragmentToSettingsFragment()
            navigate(action)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val converterApiService = retrofit.create(ConverterApi::class.java)

        converterApiService.getLatest(baseCurrency = "USD", currencies = "RUB").enqueue(object: Callback<SymbolsResponse>{
            override fun onResponse(
                call: Call<SymbolsResponse>,
                response: Response<SymbolsResponse>
            ) {
                if (response.code()==200) {
                    val result1 = "${response.body()?.data?.RUB?.code}"
                    val result2 = "${response.body()?.data?.RUB?.value}"
                    val output = "code of currency:$result1\nrate: $result2"
                    binding.testTextView.text = output
                }
                else binding.testTextView.text = response.code().toString()
            }

            override fun onFailure(call: Call<SymbolsResponse>, t: Throwable) {
                binding.testTextView.text = t.toString()
            }


        })


    }

    private fun navigate(action: NavDirections){
        binding.root.findNavController().navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val BASE_URL = "https://api.currencyapi.com"
        const val API_KEY = "m8t10hvSal3gRP9IIlLERGTxd8CgbqcUHkoKKi1Q"
    }
}