package com.example.leh2.pablo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var imgView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "http://www1.lasalle.edu/~blum/c341wks/Picasso/picasso.json"

        lateinit var title: Array<String?>
        lateinit var year: Array<String?>
        lateinit var size: Array<String?>
        lateinit var artist: Array<String?>
        lateinit var style: Array<String?>
        lateinit var image: Array<String?>

        var styleName: String?



        val sp = findViewById<Spinner>(R.id.spinner)
        imgView = findViewById(R.id.imageView)
        val tvYear = findViewById<TextView>(R.id.tvYear)
        val tvSize = findViewById<TextView>(R.id.tvSize)
        val tvArtist = findViewById<TextView>(R.id.tvArtist)
        val tvStyle = findViewById<TextView>(R.id.tvStyle)
        val btn = findViewById<Button>(R.id.button)

        val requestQueue = Volley.newRequestQueue(applicationContext)
        val request = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener<JSONObject> { response ->
                    try {
                        val array = response.getJSONArray("works")
                        title = arrayOfNulls(array.length())
                        year = arrayOfNulls(array.length())
                        size = arrayOfNulls(array.length())
                        artist = arrayOfNulls(array.length())
                        style = arrayOfNulls(array.length())

                        for (i in 0 until array.length()) {
                            val aJSONElement = array.getJSONObject(i)
                            val picTitle = aJSONElement.getString("title")
                            title[i] = picTitle
                            year[i] = aJSONElement.getString("year")
                            size[i] = aJSONElement.getString("size")
                            artist[i] = aJSONElement.getString("location")
                            style[i] = aJSONElement.getString("style")
                            image[i] = aJSONElement.getString("image")
                        }
                        sp.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, title)
                    } catch (e: IOException) {
                    }
                },
                Response.ErrorListener { error ->
                    Log.d(error.toString(), "There is some error")
                })
        requestQueue.add(request)

        sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, i: Int, p3: Long) {
                tvYear.text = year[i]
                tvSize.text = size[i]
                tvArtist.text = artist[i]
                tvStyle.text = style[i]
                styleName = style[i]
                val imgURL = image[i]
                loadImageFromUrl(imgURL)

                btn.setOnClickListener({
                    val a = styleName.replace("\\s","").toLowerCase()




                })
            }
        }
    }
    private fun loadImageFromUrl(imgURL: String?) {
        Picasso.with(this).load(imgURL).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imgView, object : com.squareup.picasso.Callback {
                    override fun onSuccess() {}

                    override fun onError() {}
                })

    }
}
