package com.example.leh2.dice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (findViewById<Button>(R.id.btnSimulate)).setOnClickListener({
            for(i in 1..5){
                //Get imageview one by one
                val myID = "imgV" + i.toString()
                val resID = resources.getIdentifier(myID, "id", packageName)
                val imgView = findViewById<View>(resID) as ImageView

                //random variable
                val value = rand(1,6)

                //get the image of the die
                val drawableResourceName = "die" + value
                val id = imgView.context.resources.getIdentifier(drawableResourceName, "drawable", imgView.context.packageName)

                //set the image for the die
                imgView.setImageResource(id)
            }
        })
    }

    //https://stackoverflow.com/a/45685145
    private fun rand(from: Int, to: Int) : Int {
        return Random().nextInt(to - from) + from
    }
}
