package com.example.leh2.jackinthebox

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.os.CountDownTimer;

import java.util.Random
import android.media.MediaPlayer
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnOpen = findViewById<Button>(R.id.btnOpen)
        val btnClose = findViewById<Button>(R.id.btnClose)
        val imgV = findViewById<ImageView>(R.id.imgV)

        btnClose.isEnabled = false

        btnOpen.setOnClickListener({
            val mp = MediaPlayer.create(baseContext, R.raw.fast)
            mp.start()

            val mTimer = object : CountDownTimer(6000, 1000) {
                override fun onFinish() {
                    val id = imgV.context.resources.getIdentifier("jack_open", "drawable", imgV.context.packageName)
                    imgV.setImageResource(id)

                    btnClose.isEnabled = true
                    btnOpen.isEnabled = false
                }
                override fun onTick(millisUntilFinished: Long) {}
            }
            mTimer.start()

            btnClose.isEnabled = true
            btnOpen.isEnabled = false
        })

        btnClose.setOnClickListener({
            btnClose.isEnabled = false
            btnOpen.isEnabled = true

            val id = imgV.context.resources.getIdentifier("jack_closed", "drawable", imgV.context.packageName)
            imgV.setImageResource(id)
        })
    }

    fun rand(from: Int, to: Int) : Int {
        return random.nextInt(to - from) + from
    }
}
