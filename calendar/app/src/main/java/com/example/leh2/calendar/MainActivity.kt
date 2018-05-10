package com.example.leh2.calendar

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.CalendarView
import android.widget.Toast
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDetermine = findViewById<Button>(R.id.BtnDetermine)
        val cvDate = findViewById<CalendarView>(R.id.cvDate)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        val sp = this.getSharedPreferences("cal", Context.MODE_PRIVATE)
        val editor = sp!!.edit()

        var myYear = sp.getInt("myYear", Calendar.YEAR)
        var myMonth = sp.getInt("myMonth", Calendar.MONTH)
        var myDay = sp.getInt("myDay", Calendar.DAY_OF_MONTH)

        Toast.makeText(this, myYear.toString()+ "/" + myMonth.toString() + "/" +myDay, Toast.LENGTH_SHORT).show()

        val display = Calendar.getInstance()
        display.set(myYear, myMonth, myDay)
        cvDate.date = display.timeInMillis
        val today = Calendar.getInstance()
        val later = Calendar.getInstance()
        later.set(myYear, myMonth, myDay)
        //Calendar later = new GregorianCalendar(year, month, day);
        val diff = (later.timeInMillis - today.timeInMillis) / (1000 * 60 * 60 * 24)
        tvResult.text = java.lang.Long.toString(diff) + " days from now."

        cvDate.setOnDateChangeListener {_, year, month, day ->
            myYear = year
            myMonth = month
            myDay = day

            editor.putInt("myYear", year)
            editor.putInt("myMonth", month)
            editor.putInt("myDay", day)

            editor.apply()

            btnDetermine.performClick()
        }

        btnDetermine.setOnClickListener({
            val today = Calendar.getInstance()
            val later = Calendar.getInstance()
            later.set(myYear, myMonth, myDay)
            //Calendar later = new GregorianCalendar(year, month, day);
            val diff = (later.timeInMillis - today.timeInMillis) / (1000 * 60 * 60 * 24)
            tvResult.text = java.lang.Long.toString(diff) + " days from now."
        })

    }
}
