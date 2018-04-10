package com.example.leh2.thanksgiving

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.*
import org.json.JSONException
import org.json.JSONObject
import android.widget.CheckBox

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lateinit var steps: String

        val sp = findViewById<Spinner>(R.id.spinner)
        val tv = findViewById<TextView>(R.id.textView)
        val imgView = findViewById<ImageView>(R.id.imageView)
        val lnlayout = findViewById<LinearLayout>(R.id.linearLayout)
        val btn = findViewById<Button>(R.id.button)

        val ins = resources.openRawResource(resources.getIdentifier("thanks", "raw", packageName))
        val inputAsString = ins.bufferedReader().use { it.readText() }

        val jsonObj = JSONObject(inputAsString)
        val JSONArray = jsonObj.getJSONArray("sides")
        val sideNamesArray = arrayOfNulls<String>(JSONArray.length())

        for (i in 0 until JSONArray.length()) {
            try {
                //get an individual element of the JSON array
                val aJSONElement = JSONArray.getJSONObject(i)

                //get the individual properties of the JSON element
                val name = aJSONElement.getString("sideName")
                sideNamesArray[i] = name

            } catch (e: JSONException) {
                Toast.makeText(baseContext, "Dude, you have to know what the JSON looks like to parse it", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }

        sp.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, sideNamesArray)
        sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, i: Int, p3: Long) {
                lnlayout.removeAllViews()
                tv.text = "Prep time: " + JSONArray.getJSONObject(i).getString("prep time") +
                          "\nCook time: " + JSONArray.getJSONObject(i).getString("cook time")
                steps = JSONArray.getJSONObject(i).getString("steps")

                val imgURL = JSONArray.getJSONObject(i).getString("image")
                val id = imgView.context.resources.getIdentifier(imgURL, "drawable", imgView.context.packageName)
                imgView.setImageResource(id)

                val ing = JSONArray.getJSONObject(i).getString("ingredients")
                val splitedString = ing.split("~")

                for (s in splitedString) {
                    val cb = CheckBox(applicationContext)
                    cb.text = s
                    lnlayout.addView(cb)
                }
            }
        }

        // adapt from https://stackoverflow.com/questions/26097513/android-simple-alert-dialog
        btn.setOnClickListener({
            val alertDialog = AlertDialog.Builder(this@MainActivity).create()
            alertDialog.setTitle("How to cook this dish?")
            alertDialog.setMessage(steps)
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK") { dialog, _ ->
                dialog.dismiss()
            }
            alertDialog.show()
        })
    }
}