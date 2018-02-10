package com.example.leh2.cookingsteak

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var size = 0.0
        var res: Double

        val btnCalc = findViewById<Button>(R.id.btnCalc)
        val radGrpDone = findViewById<RadioGroup>(R.id.radGrpDone)
        val radGrpSize = findViewById<RadioGroup>(R.id.radGrpSize)
        val tvRes = findViewById<TextView>(R.id.tvRes)

        val radioDone = radGrpDone.checkedRadioButtonId
        val radioSize = radGrpSize.checkedRadioButtonId
        Toast.makeText(applicationContext, radioDone.toString() , LENGTH_LONG).show()

//        if(radioDone==-1){
//            //if no radio button selected
//            Toast.makeText(applicationContext, "Please select a doneness level" , LENGTH_LONG).show()
//        }
//
//        if(radioSize==-1){
//            //if no radio button selected
//            Toast.makeText(applicationContext, "Please select a size" , LENGTH_LONG).show()
//        }

        btnCalc.setOnClickListener({

            when (radioSize) {
                0 -> size = 1.0
                1 -> size = 1.5
                2 -> size = 2.0
                3 -> size = 2.5
                4 -> size = 2.0
            }
           // Toast.makeText(applicationContext, size.toString() , LENGTH_LONG).show()
            res = 2 + radioDone + size * 2
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
}