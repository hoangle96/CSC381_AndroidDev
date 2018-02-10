package com.example.leh2.songlyrics

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.io.File
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val songName = arrayOf("The Twist","Smooth", "Mack the Knife", "How Do I Live", "Party Rock Anthem", "I Gotta Feeling", "Macarena", "Physical", "You Light Up My Life", "Hey Jude")
        val artist = arrayOf("Chubby Checker","Santana Featuring Rob Thomas", "Bobby Darin", "LeAnn Rimes", "LMFAO Featuring Lauren Bennett n GoonRock", "The Black Eyed Peas", "Los Del Rio", "Olivia Newton-John", "Debby Boone", "The Beatles")

        var i = 0
        while(i < 5){
                val filename = context.resources.getIdentifier(drawableResourceName, "drawable", imgVtitle.context.packageName)
                val inputStream: InputStream = File(R.raw.lyrics+"i").inputStream()
                i++



        }



    }
}
