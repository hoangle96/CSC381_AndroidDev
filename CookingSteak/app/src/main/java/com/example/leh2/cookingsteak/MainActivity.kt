package com.example.leh2.cookingsteak

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.Toast.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var size = 0.0
        var res = 0.0

        val btnCalc = findViewById<Button>(R.id.btnCalc)
        val radGrpDone = findViewById<RadioGroup>(R.id.radGrpDone)
        val radGrpSize = findViewById<RadioGroup>(R.id.radGrpSize)
        val tvRes = findViewById<TextView>(R.id.tvRes)

        btnCalc.setOnClickListener({

            val radioDone = radGrpDone.checkedRadioButtonId
            val tvDone = findViewById<TextView>(R.id.textViewdoneness)

//
            val radioSize = radGrpSize.checkedRadioButtonId
            val tvSize = findViewById<TextView>(R.id.textViewsize)
            val radioButtonSize = findViewById<RadioButton>(radioSize)

//            Toast.makeText(applicationContext, radioSize.toString(), Toast.LENGTH_LONG).show()




//            size = radGrpSize.checkedRadioButtonId.toDouble()

            size = radioButtonSize.text.toString().toDouble()
            tvSize.text = size.toString()

            val donenessIndex = radioDone - 6
            tvDone.text = donenessIndex.toString()
           // Toast.makeText(applicationContext, size.toString() , LENGTH_LONG).show()

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

        radGrpDone.setOnCheckedChangeListener({ _, checkedId ->
            res = 0.0
            tvRes.text = ""
        } )

        radGrpSize.setOnCheckedChangeListener({ _, _ ->
            res = 0.0
            tvRes.text = ""
        })
    }
}