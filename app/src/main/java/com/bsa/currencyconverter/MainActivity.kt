package com.bsa.currencyconverter

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import bsa.currencyconverter.databinding.ActivityMainBinding
import com.bsa.currencyconverter.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnConverter.setOnClickListener {
            convertCurrency()
        }


        binding.changeCurrency.setOnClickListener {

            val fromValuePos = binding.spFromCurrency.selectedItemPosition

            binding.spFromCurrency.setSelection(binding.spToCurrency.selectedItemPosition)
            binding.spToCurrency.setSelection(fromValuePos)


        }


        launchCollector()

    }

    private fun launchCollector(){

        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect { event ->

                when (event) {
                    is MainViewModel.CurrencyEvent.Success -> {
                        binding.progressBar.isVisible = false
                        binding.tvResult.setTextColor(Color.BLACK)
                        binding.tvResult.text = event.resultText
                    }

                    is MainViewModel.CurrencyEvent.Failure -> {
                        binding.progressBar.isVisible = false
                        binding.tvResult.setTextColor(Color.RED)
                        binding.tvResult.text = event.errorText
                    }
                    is MainViewModel.CurrencyEvent.Loading -> {
                        binding.progressBar.isVisible = true

                    }
                    else -> Unit
                }

            }
        }


    }


    private fun convertCurrency(){


        viewModel.convert(
            binding.etFrom.text.toString(),
            binding.etTo.text.toString(),
            binding.spFromCurrency.selectedItem.toString(),
            binding.spToCurrency.selectedItem.toString()
        )

    }

}


