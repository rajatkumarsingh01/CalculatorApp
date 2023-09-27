package com.example.calculatorapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.calculatorapp.databinding.ActivityMoreMathsBinding
import java.lang.Math.*
import java.math.BigInteger

class MoreMathsActivity : AppCompatActivity() {
    private lateinit var displayTextView: TextView
    private lateinit var binding : ActivityMoreMathsBinding
    private lateinit var digitButtons: List<Button>
    private var currentNumber: Double = 0.0
    private var secondNumber: Double =0.0
    private var pendingOperator: String = ""
    private var resultDisplayed: Boolean = false
    private var flag : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityMoreMathsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayTextView=findViewById(R.id.displayTextView)

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
            buttonfactorial.setOnClickListener {
                calculateFactorial()
            }
            buttonSquare.setOnClickListener {
                calculateSquare()
            }
            buttonCube.setOnClickListener {
                calculateCube()
            }
            buttonSquareRoot.setOnClickListener {
                calculateSquareRoot()
            }
            buttonPower.setOnClickListener {
                calculatePower()
            }
            buttonsin.setOnClickListener {
                calculateSin()
            }
            buttoncos.setOnClickListener {
                calculateCos()
            }
            buttontan.setOnClickListener {
                calculateTan()
            }
            buttonRadian.setOnClickListener {
                calulateRadian()
            }
            buttonExponent.setOnClickListener {
                calculateExponent()
            }
            buttonlog2.setOnClickListener {
                calculateLog2()
            }
            buttonlog10.setOnClickListener {
                calculateLog10()
            }
            buttonExponentValue.setOnClickListener {
                calculateExponentValue()
            }
            buttonSineInverse.setOnClickListener {
                calculateSineInverse()
            }
            buttonCosInverse.setOnClickListener {
                calculateCosInverse()
            }
            buttonPower10.setOnClickListener {
                calculatePower10()
            }

        }
    }

    private fun calculatePower10() {
        val currentText = displayTextView.text.toString()
        val value = currentText.toDouble()
        val exponent=10.0
        val result = Math.pow(exponent,value)
        displayTextView.text = result.toString()
        currentNumber = result
        resultDisplayed = true

    }

    private fun calculateCosInverse() {
        val currentText = displayTextView.text.toString().toDouble()
        val value=currentText
        if (value!= null && value >= -1.0 && value <= 1.0) {
            val CosineInverseResult = Math.acos(value)
            displayTextView.text = "${Math.toDegrees(CosineInverseResult)}"
            currentNumber = CosineInverseResult
            resultDisplayed = true
        } else {
            displayTextView.text = "Invalid input"
        }

    }

    private fun calculateSineInverse() {
        val currentText = displayTextView.text.toString().toDouble()
            val value=currentText
        if (value!= null && value >= -1.0 && value <= 1.0) {
            val SineInverseResult = Math.asin(value)
            displayTextView.text = "${Math.toDegrees(SineInverseResult)}"
            currentNumber = SineInverseResult
            resultDisplayed = true
        } else {
            displayTextView.text = "Invalid input"
        }

    }

    private fun calculateExponentValue() {
        val result=2.7182818
        displayTextView.text=result.toString()
        currentNumber=result
        resultDisplayed=true
    }

    private fun calculateLog10() {
        val currentText = displayTextView.text.toString().toDouble()
        val value=currentText
        if (value != null && value > 0) {
            val log10Result = log(value) / log(10.0)
            displayTextView.text = "$log10Result"
            currentNumber = log10Result
            resultDisplayed = true
        } else {
            displayTextView.text = "Invalid input"
        }
    }


    private fun calculateLog2() {
        val currentText = displayTextView.text.toString().toDouble()
        val value=currentText
        if (value != null && value > 0) {
            val log2Result = log(value) / log(2.0)
            displayTextView.text = "$log2Result"
            currentNumber = log2Result
            resultDisplayed = true
        } else {
            displayTextView.text = "Invalid input"
        }
    }

    private fun calculateExponent() {
        val currentText = displayTextView.text.toString()
        val value= currentText.toDouble()
        val result = exp(value)
        displayTextView.text = result.toString()
        currentNumber=result

        resultDisplayed = true
    }

    private fun calulateRadian() {
        val currentText = displayTextView.text.toString().toDouble()
        val radians= currentText
        if (radians != null) {
            // Convert radians to degrees
            val degrees = Math.toDegrees(radians)

            // Display the result
            displayTextView.text = "${degrees}"
            currentNumber=degrees
            resultDisplayed=true
        } else {
            displayTextView.text = "Invalid input"
        }




    }

    private fun calculateTan() {
        val angleDegrees = displayTextView.text.toString().toDouble()

        if (angleDegrees != null) {
            // Convert degrees to radians
            val angleRadians = Math.toRadians(angleDegrees)

            // Calculate the sine value
            val tanValue = tan(angleRadians)
            displayTextView.text = "$tanValue"
            currentNumber=tanValue
        } else {
            displayTextView.text = "Error"
        }


    }

    private fun calculateCos() {
        val angleDegrees = displayTextView.text.toString().toDouble()
        Log.d("sine","radians :"+currentNumber.toString())

        if (angleDegrees != null) {
            // Convert degrees to radians
            val angleRadians = Math.toRadians(angleDegrees)
            Log.d("cosine","radians :"+angleRadians.toString())
            Log.d("cosine","degrees :"+angleDegrees.toString())


            // Calculate the sine value
            val cosineValue = cos(angleRadians)
            Log.d("cosine"," result:"+cosineValue)
            displayTextView.text = "$cosineValue"
            currentNumber=cosineValue
        } else {
            displayTextView.text = "Error"
        }


    }

    private fun calculateSin() {

        val angleDegrees = displayTextView.text.toString().toDouble()
        Log.d("sine","radians :"+currentNumber.toString())
        if (angleDegrees != null) {
            // Convert degrees to radians
            val angleRadians = Math.toRadians(angleDegrees)
            Log.d("sine","radians :"+angleRadians.toString())
            Log.d("sine","degrees :"+angleDegrees.toString())
            // Calculate the sine value
            val sineValue = sin(angleRadians)
            Log.d("sine"," result:"+sineValue)
            displayTextView.text = "$sineValue"
            currentNumber=sineValue
        } else {
            displayTextView.text = "Error"
        }



    }

    private fun calculatePower() {
        //isme problem hai
        val currentText = displayTextView.text.toString()
        val value = currentText.toDouble()
        val exponent=2.0
        val result = Math.pow(value,exponent)
        displayTextView.text = result.toString()
        currentNumber = result
        resultDisplayed = true
    }

    private fun calculateSquareRoot() {
        val currentText = displayTextView.text.toString()
        val value = currentText.toDouble()

        if (value>= 0) {
            val result = Math.sqrt(value)
          displayTextView.text = result.toString()
            currentNumber=result

            resultDisplayed=true

        } else {
            displayTextView.text = "Error"
        }

    }

    private fun calculateCube() {
        val currentText = displayTextView.text.toString()
        val value= currentText.toDouble()
        val CubeResult=value*value*value

        displayTextView.text=CubeResult.toString()
        currentNumber=CubeResult
        resultDisplayed=true

    }


    private fun calculateSquare() {

        val currentText = displayTextView.text.toString()
        val value = currentText.toDouble()
         val squareResult=value*value

        displayTextView.text=squareResult.toString()
        currentNumber = squareResult
        resultDisplayed=true

    }

    private fun calculateFactorial() {
        val currentText = displayTextView.text.toString()
        val value = currentText.toDouble()

        if (value < 0) {
            displayTextView.text = "Error"
        } else {
            val factorialResult = factorial(value.toInt())
            displayTextView.text = factorialResult.toString()
            currentNumber = factorialResult.toDouble()
            resultDisplayed = true
        }
    }

    private fun factorial(n: Int): BigInteger {
        flag = 1
        return if (n <= 1) {
            BigInteger.ONE
        } else {
            var result = BigInteger.ONE
            for (i in 2..n) {
                result = result.multiply(BigInteger.valueOf(i.toLong()))
            }
            result
        }

}

private fun calculatePercentage() {
        val currentText = displayTextView.text.toString()
        val value= currentText.toDouble()

        // Perform the percentage calculation (divide by 100)
        val result = value / 100.0

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
        displayTextView.text ="0"
    }



    private fun calculateResult() {
        flag = 1
        val secondNumber = displayTextView.text.toString().toDouble()

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
        flag=0
        currentNumber = 0.0
        pendingOperator = ""
        displayTextView.text = "0"
    }

    override fun onBackPressed() {
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

}