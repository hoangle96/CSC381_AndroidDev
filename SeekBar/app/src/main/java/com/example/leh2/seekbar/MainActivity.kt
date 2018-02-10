package com.example.leh2.seekbar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast

class MainActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        var resultList = arrayOf(0,0,0,0,0,0)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for (i in 0..5) {
            val myID = "sB" + i
            val resID = resources.getIdentifier(myID, "id", packageName)
            val mySeekBar = findViewById<View>(resID) as SeekBar
            resultList[i] = mySeekBar.progress
            mySeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    // Write code to perform some action when progress is changed.
                    resultList[i] = progress
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {
                    // Write code to perform some action when touch is started.
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    // Write code to perform some action when touch is stopped.
                    // Toast.makeText(this@MainActivity, "Current value is " + seekBar.progress, Toast.LENGTH_SHORT).show()
                }
            })
        }

        (findViewById<Button>(R.id.btnShow)).setOnClickListener({
            var res = ""
            for(element in resultList){
                res += " " + element.toString()
            }
            (findViewById<TextView>(R.id.tVShow)).text = res

        })
    }
}



