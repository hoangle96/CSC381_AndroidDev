package com.example.leh2.fiveguys

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener


class MainActivity : AppCompatActivity() {
    var price = 0.0
    var order = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCalc = findViewById<Button>(R.id.btcCalc)
        val tvPrice = findViewById<TextView>(R.id.tvPrice)

        for (i in 0..4) {
            val myID = "chbx" + i
            val resID = resources.getIdentifier(myID, "id", packageName)
            val myCheckBox = findViewById<CheckBox>(resID)
            myCheckBox.setOnClickListener()
//            myCheckBox.setOnClickListener {
//                order = order + myCheckBox.text + "\n"
//                when (i) {
//                    0 -> price += 6.19
//                    1 -> price += 6.89
//                    2 -> price += 7.19
//                    3 -> price += 7.89
//                }
//            }
        }

        val chboxFries = findViewById<CheckBox>(R.id.chbxFries)
        if(chboxFries.isChecked){
            price += 3.79
        }

        CompoundButton.OnCheckedChangeListener { _, _ ->

        }
    }
}