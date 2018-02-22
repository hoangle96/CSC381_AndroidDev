package com.example.leh2.songlyrics

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import java.io.*
import java.nio.charset.Charset
import android.text.method.ScrollingMovementMethod



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val songName = arrayOf("The Twist","Smooth", "Mack the Knife", "How Do I Live", "Party Rock Anthem", "I Gotta Feeling", "Macarena", "Physical", "You Light Up My Life", "Hey Jude")
        val artist = arrayOf("Chubby Checker","Santana Featuring Rob Thomas", "Bobby Darin", "LeAnn Rimes", "LMFAO Featuring Lauren Bennett n GoonRock", "The Black Eyed Peas", "Los Del Rio", "Olivia Newton-John", "Debby Boone", "The Beatles")
        val mutableListLyrics = mutableListOf<String>()
        //read text
        var i = 0
        while(i < 5) {
            val filename = "lyrics"+i.toString()
            val ins = resources.openRawResource(resources.getIdentifier(filename, "raw", packageName))


            val inputAsString = ins.bufferedReader().use { it.readText() }
            val splitedString = inputAsString.split("/")
            for(s in splitedString){
                mutableListLyrics.add(s)
            }
            i++
        }

        val spName = findViewById<Spinner>(R.id.spName)
        spName.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,songName)
        val tvArtist = findViewById<TextView>(R.id.tvAuthor)
        val tvLyrics = findViewById<TextView>(R.id.tvLyrics)
        tvLyrics.setMovementMethod(ScrollingMovementMethod())

        spName.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {//fix "object is not abstract" https://www.youtube.com/watch?v=D5l7MNlqA3M
        override fun onNothingSelected(p0: AdapterView<*>?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, index: Int, p3: Long) {
                tvArtist.text = artist[index]
                tvLyrics.text = mutableListLyrics[index]
            }
        }
    }
}