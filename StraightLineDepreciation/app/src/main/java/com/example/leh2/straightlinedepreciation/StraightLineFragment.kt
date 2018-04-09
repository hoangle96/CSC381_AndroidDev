package com.example.leh2.straightlinedepreciation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class StraightLineFragment : Fragment() {
    @SuppressLint("SetTextIn")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val myView = inflater.inflate(R.layout.straight_line_fragment, container, false)

        val btnSchedule  = myView.findViewById<Button>(R.id.button)
        val etInitial = myView.findViewById<EditText>(R.id.etInitial)
        val etSalvage = myView.findViewById<EditText>(R.id.etSalvage)
        val etLifetime = myView.findViewById<EditText>(R.id.etLifetime)
        val tvSchedule = myView.findViewById<TextView>(R.id.tvSchedule)
        btnSchedule.setOnClickListener({
            tvSchedule.text = ""

            var initial = etInitial.text.toString().toDouble()

            var salvage = etSalvage.text.toString().toDouble()

            var lifetime =  etLifetime.text.toString().toInt()

            var value = initial
            var deprec = (initial - salvage) / lifetime
            tvSchedule.text = "Initial: " + initial.toString()
            for (i in 1..lifetime) {
                value -= deprec
                tvSchedule.append("\nAfter " + i.toString() + " year(s): $ " + String.format("%.2f", value))
            }
        })

        etInitial.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                tvSchedule.text = ""
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        etSalvage.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                tvSchedule.text = ""
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        etLifetime.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                tvSchedule.text = ""
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        return myView
    }
}