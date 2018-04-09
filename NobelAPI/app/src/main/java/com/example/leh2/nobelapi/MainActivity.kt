package com.example.leh2.nobelapi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    var uri = ""
    lateinit var nameArray: MutableList<String>
    lateinit var motivationArray: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvName = findViewById<TextView>(R.id.tvName)
        val tvMotivation = findViewById<TextView>(R.id.tvMotivation)
        tvMotivation.movementMethod = ScrollingMovementMethod()

        val spCategory = findViewById<Spinner>(R.id.spCategory)
        val category = arrayOf("Physics", "Chemistry", "Medicine", "Peace", "Literature", "Economics")
        spCategory.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, category)

        val spYear = findViewById<Spinner>(R.id.spYear)


        spCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, i: Int, p3: Long) {
                val year = mutableListOf<String>()
                if(i != category.size - 1){
                    for(a in 1901..2017){
                        year.add(a.toString())
                    }
                } else {
                    for(a in 1969..2017){
                        year.add(a.toString())
                    }
                }
                spYear.adapter = ArrayAdapter(baseContext, R.layout.support_simple_spinner_dropdown_item, year)
                spYear.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                    override fun onItemSelected(q0: AdapterView<*>?, q1: View?, q2: Int, q3: Long) {
                        uri = "http://api.nobelprize.org/v1/prize.json?category="+category[i].toLowerCase()+"&year="+ year[q2]
//                        Toast.makeText(applicationContext, uri.toString(), Toast.LENGTH_SHORT).show()
//                        tvName.text = uri.toString()

                        val requestQueue = Volley.newRequestQueue(applicationContext)
                        val request = JsonObjectRequest(Request.Method.GET, uri, null,
                                Response.Listener<JSONObject> { response ->
                                    try {
                                        tvName.text = ""
                                        val array = response.getJSONArray("prizes").getJSONObject(0)
                                        val temp = array.getJSONArray("laureates")
                                        for (i in 0 until temp.length()){
                                            tvName.append(temp.getJSONObject(i).getString("firstname")+ " " + temp.getJSONObject(i).getString("surname"))
                                            if(i != (temp.length() - 1)){
                                                tvName.append(", ")
                                            }
                                            tvMotivation.text = temp.getJSONObject(i).getString("motivation")
                                        }
                                    }
                                    catch (e: IOException){
                                        tvName.text = "err"
                                    }
                                },
                                Response.ErrorListener { error ->
                                    Log.d(error.toString(), "There is some error")
                                })
                        requestQueue.add(request)


                    }
                }
            }
        }


    }
}
