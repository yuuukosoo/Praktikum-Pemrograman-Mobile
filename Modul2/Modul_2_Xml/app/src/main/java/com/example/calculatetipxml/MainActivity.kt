package com.example.calculatetipxml

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.calculatetipxml.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tipResult.text = getString(R.string.tip_amount, "Rp0.00")

        val items = resources.getStringArray(R.array.tip_options_array)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        binding.tipOptions.setAdapter(adapter)

        binding.costOfServiceText.doAfterTextChanged {
            calculateTip()
        }

        binding.tipOptions.setOnItemClickListener { _, _, _, _ ->
            calculateTip()
        }

        binding.roundUpSwitch.setOnCheckedChangeListener { _, _ ->
            calculateTip()
        }

    }

    private fun calculateTip(){
        val stringInTextField = binding.costOfServiceText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        if(cost == null || cost == 0.0){
            binding.tipResult.text = getString(R.string.tip_amount, "Rp0.00")
            return
        }

        val selectedTipText = binding.tipOptions.text.toString().replace("%", "")
        val tipPercentage = selectedTipText.toDoubleOrNull() ?: 15.0

        var tip = tipPercentage / 100 * cost

        if(binding.roundUpSwitch.isChecked){
            tip = kotlin.math.ceil(tip)

        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}