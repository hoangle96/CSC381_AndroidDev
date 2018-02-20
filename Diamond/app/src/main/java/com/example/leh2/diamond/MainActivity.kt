package com.example.leh2.diamond

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.widget.Toast.LENGTH_LONG

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var res: Double

        val btnCalc = findViewById<Button>(R.id.btnCalc)
        val tvRes = findViewById<TextView>(R.id.tvRes)
        val caratEditable = findViewById<EditText>(R.id.eTCarat)
        val carat = caratEditable.text.toString().toDouble()
        if(carat.equals(0)){
            Toast.makeText(applicationContext, "Please enter a value for carat" , LENGTH_LONG).show()
        }

        val rGColor = findViewById<RadioGroup>(R.id.rGColor)
        val rGClarity = findViewById<RadioGroup>(R.id.rGClarity)

        val colorID = rGColor.checkedRadioButtonId
        val color = colorID + 1
        val clarityID = rGClarity.checkedRadioButtonId
        val clarity = clarityID + 1

        if(colorID==-1){
            //if no radio button selected
            Toast.makeText(applicationContext, "Please select a color" , LENGTH_LONG).show()
        }

        if(clarityID==-1){
            //if no radio button selected
            Toast.makeText(applicationContext, "Please select a clarity level" , LENGTH_LONG).show()
        }

        btnCalc.setOnClickListener({
            res = 137 + 4020*carat - 244*(clarity+1) - 68 * color * color - 50 * color
            tvRes.text = res.toString()
        })


        rGColor.setOnCheckedChangeListener({ _, _ ->
            res = 0.0
            tvRes.text = ""
        } )

        rGClarity.setOnCheckedChangeListener({ _, _ ->
            res = 0.0
            tvRes.text = ""
        })
    }
}