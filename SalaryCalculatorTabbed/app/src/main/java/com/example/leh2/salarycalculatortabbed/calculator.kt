package com.example.leh2.salarycalculatortabbed


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG

class Calculator : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val myView = inflater.inflate(R.layout.fragment_main, container, false)

        val sp = this.context!!.getSharedPreferences("salary", Context.MODE_PRIVATE)
        val editor = sp!!.edit()

        val btnShow = myView.findViewById<Button>(R.id.btnShow)
        val inputHr = myView.findViewById<EditText>(R.id.inputTxtHr)
        val inputRate = myView.findViewById<EditText>(R.id.inputTxtRate)
//        val etHour = findViewById<EditText>(R.id.editTxtHr)
//        val etRate = findViewById<EditText>(R.id.editTxtRate)
        val txtView = myView.findViewById<TextView>(R.id.txtView)

        val savedHour = sp.getFloat("hour", 0f)
        val savedRate = sp.getFloat("rate", 0f)
//        val savedSalary = sp.getInt("salary", 0)

        inputHr.setText("" + savedHour)
        inputRate.setText("" + savedRate)
//        txtView.text = "" + savedSalary

        btnShow.setOnClickListener{
            val hour = inputHr.text.toString().toFloat()
            val rate = inputRate.text.toString().toFloat()
            var result : Float
            result = if(hour > 40){
                rate * 40.00f + rate * (hour - 40.00f) * 1.5f
            } else{
                hour * rate
            }
            txtView.text  = "$" + String.format("%.2f", result)

            if(hour > 84){
                Toast.makeText(activity, "You work too much!", LENGTH_LONG).show()
            }

            editor.putFloat("hour", hour)
            editor.putFloat("rate", rate)
//            editor.putFloat("salary", result)

            editor.apply()
        }

        inputHr.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                txtView.text = "Salary"
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        inputRate.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                txtView.text = "Salary"
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })



        return myView
    }
}