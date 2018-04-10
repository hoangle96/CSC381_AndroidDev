package com.example.leh2.fiveguys

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    var price = 0.0
    var order = ""
    lateinit var tvOrder: TextView
    lateinit var btnCalc: Button
    var side = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCalc = findViewById<Button>(R.id.btcCalc)
        val tvPrice = findViewById<TextView>(R.id.tvPrice)
        tvOrder = findViewById<TextView>(R.id.tvOrder)

        val radioBurger = findViewById<RadioGroup>(R.id.rGrpBurger)
        val radioDoneness = findViewById<RadioGroup>(R.id.radioGroup2)

        for (i in 0..3) {
            val myID = "chbx" + i
            val resID = resources.getIdentifier(myID, "id", packageName)
            val myCheckBox = findViewById<CheckBox>(resID)
            myCheckBox.setOnCheckedChangeListener(myCheckedChange)

        }


        btnCalc.setOnClickListener({
            tvOrder.text = ""
            //get type of burger
            val burgerID = radioBurger.checkedRadioButtonId
            val burgerView = findViewById<RadioButton>(burgerID)
            val positionID = radioBurger.indexOfChild(burgerView)

            //get doneness of burger
            val doneness = radioDoneness.checkedRadioButtonId
            val donenessView = findViewById<RadioButton>(doneness)
            val positionDoneness = radioDoneness.indexOfChild(donenessView)

            //ugly code, will fix
            price = when(positionID){
                0 ->  6.19
                1 -> 6.89
                2 -> 7.19
                3 -> 7.89
                else -> 0.00
            }

            order = when(positionID){
                0 -> "Hamburger"
                1 -> "Cheeseburger"
                2 -> "Bacon Hamburger"
                3 -> "Bacon Cheeseburger"
                else -> "Error"

            }

            order += when(positionDoneness){
                0 -> ", rare"
                1 -> ", medium rare"
                2 -> ", medium well"
                3 -> ", well"
                else -> ", error"
            }

            //fries
            val chboxFries = findViewById<CheckBox>(R.id.chbxFries)
            if(chboxFries.isChecked){
                price += 3.79
                order += ", fries \n"
            }

            for (item in side){
                order += item + "\n"
            }

            tvOrder.append(order)
//            tvOrder.append(side.toString())
            tvPrice.text = price.toString()
        })
    }

    private val myCheckedChange = CompoundButton.OnCheckedChangeListener { buttonView, check ->
        if(check){
            side.add(buttonView.text.toString())
        } else {
            side.remove(buttonView.text.toString())
        }
        btnCalc.performClick()
    }
}