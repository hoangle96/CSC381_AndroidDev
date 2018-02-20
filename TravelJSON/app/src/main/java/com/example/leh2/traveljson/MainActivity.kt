package com.example.leh2.traveljson

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import org.json.JSONObject
import android.widget.Spinner
import android.widget.TextView



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val json = JSONObject()

        val tvSire = findViewById<TextView>(R.id.tvSire)
        val tvTrainer = findViewById<TextView>(R.id.tvTrainer)
        val ivSilks = findViewById<ImageView>(R.id.imgV)
        val btnClip = findViewById<Button>(R.id.btnShow)
        val spHorse = findViewById<Spinner>(R.id.spHorse)

       val inStream = resources.openRawResource(R.raw.Sites)



    }
}
