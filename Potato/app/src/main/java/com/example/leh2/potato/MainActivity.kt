package com.example.leh2.potato

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    val arraySize = Array<Int>(8) {i -> i+5}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spSize = findViewById<Spinner>(R.id.spinnerSize)
        spSize.adapter = ArrayAdapter<Int>(this, android.R.layout.simple_spinner_item, arraySize)

        val spTopping = findViewById<Spinner>(R.id.spinnerTopping)
        spTopping.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.toppings))
        val btnCalc = findViewById<Button>(R.id.btnCalc)
        val txtRes = findViewById<TextView>(R.id.textRes)


        btnCalc.setOnClickListener {
            val size = spSize.selectedItemId
            var calFromSize = (size+5)*25
            val topping = spTopping.selectedItem.toString()
            var calorie = 0
            when{
                topping == "Nothing" -> calorie = 0
                topping == "Butter" -> calorie = 100
                topping == "Sour Cream" -> calorie = 60
                topping == "Chilli" -> calorie = 75
            }
            val res = calFromSize+calorie
            txtRes.text= res.toString()
        }

    }
}
