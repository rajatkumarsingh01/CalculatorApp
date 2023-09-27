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
    private lateinit var binding : ActivityMainBinding
    private lateinit var displayTextView: TextView

    private lateinit var digitButtons: List<Button>
    private var currentNumber: Double = 0.0
    private var pendingOperator: String = ""
   // private var newNumber: String = ""
    private var resultDisplayed: Boolean = false
    private var flag : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayTextView = findViewById(R.id.displayTextView)




        binding.buttonMore.setOnClickListener {
            val intent=Intent(this,MoreMathsActivity::class.java)
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
                Log.d("main","digit typed  : "+digit.toString())
                appendDigitToTextView(digit)
                if(flag==0){
                    currentNumber = digit.toDouble()
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
            val result = 3.14159
            displayTextView.text = result.toString()
            currentNumber = result
    }

    private fun calculatePercentage() {
        val currentText = displayTextView.text.toString()
        val value = currentText.toDouble()
        // Perform the percentage calculation (divide by 100)
        val result = value/ 100.0
        // Update the display with the result
        displayTextView.text = result.toString()
        currentNumber=result

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
        val newText = if (currentText == "0") {
            digit
        } else {
            currentText + digit
        }
        displayTextView.text = newText
         resultDisplayed = false

    }
    private fun setOperator(operator: String) {
        calculateResult()
        pendingOperator = operator
       // newNumber = displayTextView.text.toString()
        displayTextView.text ="0"
    }



    private fun calculateResult() {
        flag = 1
        val secondNumber = displayTextView.text.toString().toDouble()
        Log.d("main","calculated ")
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
                  // newNumber = ""
                resultDisplayed = true

        }

     private fun clearAll() {
            flag = 0
            currentNumber = 0.0
            pendingOperator = ""
          //  newNumber = ""
            displayTextView.text = "0"
        }



    }




















