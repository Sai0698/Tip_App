package com.ksappdev.tipapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ksappdev.tipapp.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculate.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val stringInTextField = binding.etCostOfService.text.toString()
        val cost = stringInTextField.toDouble()
        val tipPercentage = when (binding.radiogroup.checkedRadioButtonId) {
            R.id.amazingservice -> 0.20
            R.id.goodservice -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        val roundUp = binding.roundSwitch.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipresult.text = getString(R.string.tip_amount, formattedTip)
    }
}