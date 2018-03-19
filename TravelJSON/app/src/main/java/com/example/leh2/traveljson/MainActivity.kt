package com.example.leh2.traveljson

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import org.json.JSONObject
import org.json.JSONException
import android.content.Intent
import android.net.Uri
import android.text.method.ScrollingMovementMethod


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvCity = findViewById<TextView>(R.id.tvCity)
        val tvCountry = findViewById<TextView>(R.id.tvCountry)
        val tvDes = findViewById<TextView>(R.id.tvDes)
        tvDes.movementMethod = ScrollingMovementMethod()
        val btnShow = findViewById<Button>(R.id.btnShow)
        val spSite = findViewById<Spinner>(R.id.spSite)
        val imgMap = findViewById<ImageView>(R.id.imgMap)
        val imgSite = findViewById<ImageView>(R.id.imgSite)

//       val inStream = resources.openRawResource(R.raw.sites)
        val ins = resources.openRawResource(resources.getIdentifier("sites", "raw", packageName))
        val inputAsString = ins.bufferedReader().use { it.readText() }

        val jsonObj = JSONObject(inputAsString)
        val JSONArray = jsonObj.getJSONArray("sites")
        val siteNameArray = arrayOfNulls<String>(JSONArray.length())
        for (i in 0 until JSONArray.length()) {
            try {
                //get an individual element of the JSON array
                val aJSONElement = JSONArray.getJSONObject(i)

                //get the individual properties of the JSON element
                val siteName = aJSONElement.getString("name")
                siteNameArray[i] = siteName

//                val siteCity = aJSONElement.getString("city")
//                val siteCountry = aJSONElement.getString("country")
//                val siteDes = aJSONElement.getString("description")
//                val siteImg = aJSONElement.getString("image")
//                val siteMap = aJSONElement.getString("map")
//                val siteVideoLink = aJSONElement.getString("youtube")

            } catch (e: JSONException) {
                Toast.makeText(baseContext, "Dude, you have to know what the JSON looks like to parse it", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }

        spSite.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, siteNameArray)
        spSite.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                tvCity.text = JSONArray.getJSONObject(p2).getString("city")
                tvCountry.text = JSONArray.getJSONObject(p2).getString("country")
                tvDes.text = JSONArray.getJSONObject(p2).getString("description")
                val imgMapid = resources.getIdentifier(JSONArray.getJSONObject(p2).getString("map"), "drawable", packageName)
                imgMap.setImageResource(imgMapid)
                val imgid = resources.getIdentifier(JSONArray.getJSONObject(p2).getString("image"), "drawable", packageName)
                imgSite.setImageResource(imgid)

            }
        }

        btnShow.setOnClickListener({

            val id = spSite.selectedItemPosition
            //Toast.makeText(getApplicationContext(), ""+id, Toast.LENGTH_LONG).show();

            try {
                val ytc = JSONArray.getJSONObject(id).getString("youtube")
                //Toast.makeText(getApplicationContext(), ytc, Toast.LENGTH_LONG).show();
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + ytc)))
            } catch (e: JSONException) {

            }


        })
    }
}