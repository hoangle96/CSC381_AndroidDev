package com.example.leh2.songlyrics

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.io.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val songName = arrayOf("The Twist","Smooth", "Mack the Knife", "How Do I Live", "Party Rock Anthem", "I Gotta Feeling", "Macarena", "Physical", "You Light Up My Life", "Hey Jude")
        val artist = arrayOf("Chubby Checker","Santana Featuring Rob Thomas", "Bobby Darin", "LeAnn Rimes", "LMFAO Featuring Lauren Bennett n GoonRock", "The Black Eyed Peas", "Los Del Rio", "Olivia Newton-John", "Debby Boone", "The Beatles")
        val mutableListLyrics = mutableListOf<String>()
        var i = 0
        while(i < 5) {
            val filename = "lyrics"+i.toString()
            i++

            val ins = resources.openRawResource(resources.getIdentifier(filename, "raw", packageName))
            
            ins.bufferedReader(



            )





//            if (fileList().contains(filename)) {
//                try {
//                    val file = InputStreamReader(openFileInput(filename))
//                    val br = BufferedReader(file)
//                    var line = br.readLine()
//                    val all = StringBuilder()
//                    while (line != null) {
//                        all.append(line + "\n")
//                        line = br.readLine()
//                    }
//                    br.close()
//                    file.close()
//                } catch (e: IOException) {
//                }
//            }


        }
    }
}