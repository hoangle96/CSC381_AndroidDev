package com.example.leh2.dynamicradiobutton

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.widget.Toast.LENGTH_LONG

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createSizeChoices()
        createDonenessChoices()
        onclickButton()
    }

    private fun onclickButton() {
        var size = 0.0
        var res = 0.0

        val btnCalc = findViewById<Button>(R.id.btnCalc)
        val radGrpDone = findViewById<RadioGroup>(R.id.radGrpDone)
        val radGrpSize = findViewById<RadioGroup>(R.id.radGrpSize)
        val tvRes = findViewById<TextView>(R.id.tvRes)

        btnCalc.setOnClickListener({

            val radioDone = radGrpDone.checkedRadioButtonId


            val radioSize = radGrpSize.checkedRadioButtonId

            val radioButtonSize = findViewById<RadioButton>(radioSize)

            size = radioButtonSize.text.toString().toDouble()

            val donenessIndex = radioDone - 6

            res = 2 + donenessIndex + size * 2
            if(radioDone==-1){
                Toast.makeText(applicationContext, "Please select a doneness level" , LENGTH_LONG).show()
                res = 0.0
            }

            if(radioSize==-1){
                //if no radio button selected
                Toast.makeText(applicationContext, "Please select a size" , LENGTH_LONG).show()
                res = 0.0
            }
            tvRes.text = res.toString()
        })

        radGrpDone.setOnCheckedChangeListener({ _, _ ->
            res = 0.0
            tvRes.text = ""
        } )

        radGrpSize.setOnCheckedChangeListener({ _, _ ->
            res = 0.0
            tvRes.text = ""
        })
    }

    private fun createDonenessChoices() {
        //TODO: To change body of created functions use File | Settings | File Templates.
        val radioGroup = findViewById<RadioGroup>(R.id.radGrpDone)
        val donenessArray = resources.getStringArray(R.array.doneness)
        for(option in donenessArray){
            val radioButton = RadioButton(this)
            radioButton.text = option
            radioGroup.addView(radioButton)
        }
    }

    private fun createSizeChoices() {
        val radioGroup = findViewById<RadioGroup>(R.id.radGrpSize)
        val sizeArray = resources.getStringArray(R.array.thickness)
        for(option in sizeArray){
            val radioButton = RadioButton(this)
            radioButton.text = option
            radioGroup.addView(radioButton)
        }
    }
}