package com.example.leh2.anotherbook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spBook = findViewById<Spinner>(R.id.spTitle)
        val tVAuthor = findViewById<TextView>(R.id.tVAuthor)
        img = findViewById<ImageView>(R.id.imgV)
        val tvGenre = findViewById<TextView>(R.id.tvGenre)

        val ins = resources.openRawResource(resources.getIdentifier("books", "raw", packageName))
        val inputAsString = ins.bufferedReader().use { it.readText() }

        val jsonObj = JSONObject(inputAsString)
        val JSONArray = jsonObj.getJSONArray("books")
        val bookTitleArray = arrayOfNulls<String>(JSONArray.length())

        for (i in 0 until JSONArray.length()) {
            try {
                //get an individual element of the JSON array
                val aJSONElement = JSONArray.getJSONObject(i)

                //get the individual properties of the JSON element
                val bookTitles = aJSONElement.getString("title")
                bookTitleArray[i] = bookTitles

            } catch (e: JSONException) {
                Toast.makeText(baseContext, "Dude, you have to know what the JSON looks like to parse it", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }

        spBook.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, bookTitleArray)
        spBook.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, i: Int, p3: Long) {
                tVAuthor.text = JSONArray.getJSONObject(i).getString("author")
                tvGenre.text = JSONArray.getJSONObject(i).getString("genre")

                val imgURL = JSONArray.getJSONObject(i).getString("cover")
                loadImageFromUrl(imgURL);
            }

        }
    }

    private fun loadImageFromUrl(imgURL: String) {
    Picasso.with(this).load(imgURL).placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(img, object : com.squareup.picasso.Callback {
            override fun onSuccess() {

            }
                override fun onError() {

                    }
                })
    }
}
