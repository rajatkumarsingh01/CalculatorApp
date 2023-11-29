package com.example.calculatorapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.calculatorapp.databinding.ActivityMainBinding
import kotlin.math.PI

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var displayTextView: TextView

    private lateinit var digitButtons: List<Button>
    private var currentNumber: Double = 0.0
    private var pendingOperator: String = ""
    private var resultDisplayed: Boolean = false
    private var flag: Int = 0

    companion object {
        private const val KEY_CURRENT_NUMBER = "current_number"
        private const val KEY_PENDING_OPERATOR = "pending_operator"
        private const val KEY_RESULT_DISPLAYED = "result_displayed"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayTextView = findViewById(R.id.displayTextView)

        if (savedInstanceState != null) {
            // Restore state
            currentNumber = savedInstanceState.getDouble(KEY_CURRENT_NUMBER, 0.0)
            pendingOperator = savedInstanceState.getString(KEY_PENDING_OPERATOR, "")
            resultDisplayed = savedInstanceState.getBoolean(KEY_RESULT_DISPLAYED, false)

            // Update UI with the restored state
            displayTextView.text = currentNumber.toString()
        }

        binding.buttonMore.setOnClickListener {
            val intent = Intent(this, MoreMathsActivity::class.java)
            startActivity(intent)
        }

        digitButtons = listOf(
            binding.button0,
            binding.button1,
            binding.button2,
            binding.button3,
            binding.button4,
            binding.button5,
            binding.button6,
            binding.button7,
            binding.button8,
            binding.button9,
        )

        for (button in digitButtons) {
            button.setOnClickListener {
                val digit = button.text.toString()

                appendDigitToTextView(digit)
                if (flag == 0) {
                    currentNumber = displayTextView.text.toString().toDouble()
                }
            }
        }

        binding.apply {
            buttonDot.setOnClickListener {
                appendDecimalToTextView()
            }

            buttonPlus.setOnClickListener {
                setOperator("+")
            }

            buttonMinus.setOnClickListener {
                setOperator("-")
            }

            buttonMultiply.setOnClickListener {
                setOperator("*")
            }

            buttonDivide.setOnClickListener {
                setOperator("/")
            }

            buttonEqual.setOnClickListener {
                calculateResult()
            }

            buttonPercentage.setOnClickListener {
                calculatePercentage()
            }

            buttonClear.setOnClickListener {
                clearAll()
            }

            buttonPI.setOnClickListener {
                calculatePI()
            }
        }
    }

    private fun calculatePI() {
        val result = PI
        displayTextView.text = result.toString()
        currentNumber = result
    }

    private fun calculatePercentage() {
        val currentText = displayTextView.text.toString()
        val value = currentText.toDouble()
        // Perform the percentage calculation (divide by 100)
        val result = value / 100.0
        // Update the display with the result
        displayTextView.text = result.toString()
        currentNumber = result
    }

    private fun appendDecimalToTextView() {
        val currentText = displayTextView.text.toString()
        // Check if the current text already contains a decimal point
        if (!currentText.contains(".")) {
            val newText = if (resultDisplayed) {
                "0."
            } else {
                currentText + "."
            }
            displayTextView.text = newText
            resultDisplayed = false
        }
    }

    private fun appendDigitToTextView(digit: String) {
        val currentText = displayTextView.text.toString()

        if (resultDisplayed) {
            // If a result is displayed, start a new number
            displayTextView.text = digit
            resultDisplayed = false
        } else {
            // If the current text is "0" or an operator is pending, replace it with the new digit
            if(currentText != "0"){
                displayTextView.text = currentText + digit

            }
            else if (currentText == "0" || pendingOperator.isNotEmpty()) {
                displayTextView.text = digit
            } else {
                // Otherwise, append the new digit to the existing text
                displayTextView.text = currentText + digit
            }
        }
    }

    private fun setOperator(operator: String) {
        calculateResult()
        pendingOperator = operator
        //displayTextView.text = "0"
    }

    private fun calculateResult() {
        flag = 1
        val secondNumber = displayTextView.text.toString().toDouble()

        Log.d("mainii", secondNumber.toString())
        when (pendingOperator) {
            "+" -> currentNumber += secondNumber
            "-" -> currentNumber -= secondNumber
            "*" -> currentNumber *= secondNumber
            "/" -> {
                if (secondNumber != 0.0) {
                    currentNumber /= secondNumber
                } else {
                    displayTextView.text = "Error"
                    return
                }
            }
        }
        displayTextView.text = currentNumber.toString()
        resultDisplayed = true
    }

    private fun clearAll() {
        flag = 0
        currentNumber = 0.0
        pendingOperator = ""
        displayTextView.text = "0"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        // Save the current state
        outState.putDouble(KEY_CURRENT_NUMBER, currentNumber)
        outState.putString(KEY_PENDING_OPERATOR, pendingOperator)
        outState.putBoolean(KEY_RESULT_DISPLAYED, resultDisplayed)

        // Call the superclass to save any view hierarchy
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        // Restore any additional UI state if needed
        // For example, if you have additional UI components, you can restore their state here
    }
}
