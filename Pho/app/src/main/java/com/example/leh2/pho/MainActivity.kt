package com.example.leh2.pho

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import org.w3c.dom.Text
import android.widget.TextView
import android.widget.ScrollView
import android.text.method.ScrollingMovementMethod
import java.io.IOException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get array from string.xml
        val address = resources.getStringArray(R.array.Address)
        val phoneNumber = resources.getStringArray(R.array.phone)
        val placeName = resources.getStringArray(R.array.name)
        val picName = resources.getStringArray(R.array.picName)


        val spPho = findViewById<Spinner>(R.id.spPho)
        val tvAddress = findViewById<TextView>(R.id.tvAddress)
        val tvPhone = findViewById<TextView>(R.id.tvPhone)
        val tvReview = findViewById<TextView>(R.id.tvReview)
        tvReview.movementMethod = ScrollingMovementMethod()
        val sbReview = findViewById<SeekBar>(R.id.seekBar)
        val img = findViewById<ImageView>(R.id.imageView)
        val tvRate = findViewById<TextView>(R.id.tvRate)

        spPho.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.name))
        spPho.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {//fix "object is not abstract" https://www.youtube.com/watch?v=D5l7MNlqA3M
        override fun onNothingSelected(p0: AdapterView<*>?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
//
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                tvAddress.text = address[p2].toString()
                tvPhone.text = phoneNumber[p2]
                val filename = picName[p2].toString()
                try {
                    val ins = resources.openRawResource(resources.getIdentifier(filename, "raw", packageName))
                    val inputAsString = ins.bufferedReader().use { it.readText() }
                    tvReview.text = inputAsString
                    val id = img.context.resources.getIdentifier(filename, "drawable", img.context.packageName)
                    img.setImageResource(id)
                }
                catch (e: IOException){
                    Toast.makeText(applicationContext, "Problem reading file ", Toast.LENGTH_LONG).show();
                }
            }
        }

        sbReview.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // Write code to perform some action when progress is changed.

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is started.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is stopped.
                // Toast.makeText(this@MainActivity, "Current value is " + seekBar.progress, Toast.LENGTH_SHORT).show()
                tvRate.text = seekBar.progress.toString()
                if(seekBar.progress < 40){
                    Toast.makeText(this@MainActivity, "Sorry to hear", Toast.LENGTH_SHORT).show()
                }
                else if (seekBar.progress < 80){
                    Toast.makeText(this@MainActivity, "So all right?", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this@MainActivity, "So you would recommend it then?", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
