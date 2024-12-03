package com.example.task3_koala

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.AppCompatToggleButton
import androidx.core.text.isDigitsOnly
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var georgian: MutableMap<Int, String> = mutableMapOf()
    var english: MutableMap<Int, String> = mutableMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        georgianDictionary() // georgian and english dictionaries are ready!
        englishDictionary()

        var translateBtn: AppCompatButton = findViewById(R.id.translateBtn)
        var inputEditText: AppCompatEditText = findViewById<AppCompatEditText>(R.id.inputET)
        var outputTextView: AppCompatTextView = findViewById<AppCompatTextView>(R.id.outoutTV)
        var toggleButton: AppCompatToggleButton = findViewById(R.id.tbTranslate)

        translateBtn.setOnClickListener() {
            if (toggleButton.isChecked) {
                when {
                    !inputEditText.text.toString().isDigitsOnly() ||
                            inputEditText.text.toString().length == 0 -> Toast.makeText(
                        this,
                        "Digits Only!",
                        Toast.LENGTH_SHORT
                    ).show()

                    inputEditText.text.toString().length > 10 -> Toast.makeText(
                        this,
                        "The size of the number is too large for Int",
                        Toast.LENGTH_SHORT
                    ).show()

                    inputEditText.text.toString().toInt() < 1 || inputEditText.text.toString()
                        .toInt() > 1000 -> Toast.makeText(
                        this,
                        "Insert a number between 1 and 1000",
                        Toast.LENGTH_SHORT
                    ).show()


                    else -> {
                        val inputNumber = inputEditText.text.toString().toInt()
                        if (inputEditText.text.toString()[0] == '0') Toast.makeText(
                            this,
                            "input starts with 0!",
                            Toast.LENGTH_SHORT
                        ).show()
                        else outputTextView.text = english[inputNumber].toString()
                    }
                }
            } else {
                when {
                    !inputEditText.text.toString().isDigitsOnly() ||
                            inputEditText.text.toString().length == 0 -> Toast.makeText(
                        this,
                        "შეიყვანეთ მხოლოდ ციფრები",
                        Toast.LENGTH_SHORT
                    ).show()

                    inputEditText.text.toString().length > 10 -> Toast.makeText(
                        this,
                        "შემომავალი რიცხვების ზომა არის დიდი!",
                        Toast.LENGTH_SHORT
                    ).show()

                    inputEditText.text.toString().toInt() < 1 || inputEditText.text.toString()
                        .toInt() > 1000 -> Toast.makeText(
                        this,
                        "შეიყვანეთ რიცხვი 1-დან 1000-ის ჩათვლით",
                        Toast.LENGTH_SHORT
                    ).show()


                    else -> {
                        val inputNumber = inputEditText.text.toString().toInt()
                        if (inputEditText.text.toString()[0] == '0') Toast.makeText(
                            this,
                            "შემომავალი რიცხვითი მნიშვნელობა იწყება 0-ით!",
                            Toast.LENGTH_SHORT
                        ).show()
                        else outputTextView.text = georgian[inputNumber].toString()
                    }
                }

            }
        }

    }

    private fun georgianDictionary() {
        val georgianUnits =
            listOf("", "ერთი", "ორი", "სამი", "ოთხი", "ხუთი", "ექვსი", "შვიდი", "რვა", "ცხრა")
        val georgianTens = listOf(
            "",
            "ათი",
            "ოცდა",
            "ოცდა",
            "ორმოცდა",
            "ორმოცდა",
            "სამოცდა",
            "სამოცდა",
            "ოთხმოცდა",
            "ოთხმოცდა"
        )
        val georgianAdditionalNumbers = listOf(
            "",
            "თერთმეტი",
            "თორმეტი",
            "ცამეტი",
            "თოთხმეტი",
            "თხუთმეტი",
            "თექვსმეტი",
            "ჩვიდმეტი",
            "თვრამეტი",
            "ცხრამეტი"
        )
        val georgianHundreds = listOf(
            "",
            "ას",
            "ორას",
            "სამას",
            "ოთხას",
            "ხუთას",
            "ექვსას",
            "შვიდას",
            "რვაას",
            "ცხრაას"
        )
        val georgianThousand = "ათასი"

        for (i in 1..1000) {
            var ans: String = ""
            when {
                i == 1000 -> ans = georgianThousand
                i < 10 -> ans = georgianUnits[i]
                i == 10 -> ans = georgianTens[1]
                i in 11..19 -> ans = georgianAdditionalNumbers[i % 10]
                i in 20..99 -> {
                    val tens = i / 10
                    val units = i % 10

                    if (tens % 2 == 0 && units == 0) {
                        ans = georgianTens[tens].substring(
                            0,
                            georgianTens[tens].length - 2
                        ) + 'ი' // + "ათი"
                    }

                    if (tens % 2 == 0 && units > 0) {
                        ans = georgianTens[tens] + georgianUnits[units]
                    }

                    if (tens % 2 == 1 && units == 0) {
                        ans = georgianTens[tens - 1] + georgianTens[1] // + "ათი"
                    }

                    if (tens % 2 == 1 && units > 0) {
                        ans = georgianTens[tens] + georgianAdditionalNumbers[units]
                    }
                }

                else -> {
                    val hundreds = (i / 100) % 10
                    val tens = (i / 10) % 10
                    val units = i % 10

//                ans = georgianHundreds[hundreds] + georgianTens[tens] + georgianUnits[units]
                    if (tens % 2 == 0 && units == 0) {
                        if (tens > 1) { //if else extra? -\./-
                            ans = georgianHundreds[hundreds] + georgianTens[tens].substring(
                                0,
                                georgianTens[tens].length - 2
                            ) + 'ი'
                        } else {
                            ans =
                                georgianHundreds[hundreds] + georgianTens[tens] + 'ი' // + "ათი" base code
                        }

                    }

                    if (tens % 2 == 0 && units > 0) {
                        ans = georgianHundreds[hundreds] + georgianTens[tens] + georgianUnits[units]
                    }

                    if (tens % 2 == 1 && units == 0) {
                        ans =
                            georgianHundreds[hundreds] + georgianTens[tens - 1] + georgianTens[1] // + "ათი"
                    }

                    if (tens % 2 == 1 && units > 0) {
                        ans =
                            georgianHundreds[hundreds] + georgianTens[tens] + georgianAdditionalNumbers[units]
                    }

                    if (tens == 1 && units > 0) {
                        ans =
                            georgianHundreds[hundreds] + georgianTens[0] + georgianAdditionalNumbers[units]
                    }
                }
            }
            georgian[i] = ans
        }
    }

    private fun englishDictionary() {
        val englishUnits =
            listOf("", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        val englishTens = listOf(
            "",
            "ten",
            "twenty",
            "thirty",
            "forty",
            "fifty",
            "sixty",
            "seventy",
            "eighty",
            "ninety"
        )
        val englishAdditionalNumbers = listOf(
            "",
            "eleven",
            "twelve",
            "thirteen",
            "fourteen",
            "fifteen",
            "sixteen",
            "seventeen",
            "eighteen",
            "nineteen"
        )
        val thousand = "thousand"

        for (i in 1..1000) {
            var ans = ""
            when {
                i == 1000 -> ans = thousand
                i < 10 -> ans = englishUnits[i]
                i in 11..19 -> ans = englishAdditionalNumbers[i % 10]
                i in 20..99 -> {
                    val tens = i / 10
                    val units = i % 10

                    if (units == 0) {
                        ans = englishTens[tens]
                    } else {
                        ans = englishTens[tens] + " " + englishUnits[units]
                    }
                }

                else -> {
                    val hundred = i / 100
                    val tens = (i / 10) % 10
                    val units = i % 10

                    ans = if(units == 0) englishUnits[hundred] + " hundred " + englishTens[tens]  else {
                        if(tens == 1) englishUnits[hundred] + " hundred " + englishAdditionalNumbers[units]
                        else englishUnits[hundred] + " hundred " + englishTens[tens] + "-" + englishUnits[units]
                    }
                }
            }

            english[i] = ans
        }
    }
}