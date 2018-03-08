package com.example.leh2.jsononline

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.json.JSONArray
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.squareup.picasso.Picasso
import org.json.JSONObject
import org.json.JSONException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val url = "http://www1.lasalle.edu/~blum/c349wks/IMDB/IMDB.json"
    lateinit var movieTitleArray: Array<String?>
    lateinit var movieYearArray: Array<String?>
    lateinit var movieDirectorArray: Array<String?>
    lateinit var movieRankArray: Array<String?>
    lateinit var movieDescArray: Array<String?>
    lateinit var moviePosterArray: Array<String?>
    lateinit var movieStarsArray: Array<String?>
    lateinit var movieyoutubeCodeArray: Array<String?>

    lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spTitle = findViewById<Spinner>(R.id.spTitle)
        val tvInfo = findViewById<TextView>(R.id.tvInfo)
        val tvDes = findViewById<TextView>(R.id.tvDes)
        img = findViewById<ImageView>(R.id.img)
        val btnShow = findViewById<Button>(R.id.btnClip)
//
//        //Getting JSON Online
//
        val requestQueue = Volley.newRequestQueue(applicationContext)
        val request = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener<JSONObject> { response ->
                    try{
                        // Get the JSON array
                        val array = response.getJSONArray("IMDBTop")
                        movieTitleArray = arrayOfNulls<String>(array.length())
                        movieYearArray = arrayOfNulls<String>(array.length())
                        movieDirectorArray = arrayOfNulls<String>(array.length())
                        movieRankArray = arrayOfNulls<String>(array.length())
                        movieDescArray = arrayOfNulls<String>(array.length())
                        moviePosterArray = arrayOfNulls<String>(array.length())
                        movieStarsArray = arrayOfNulls<String>(array.length())
                        movieyoutubeCodeArray = arrayOfNulls<String>(array.length())

                        for (i in 0 until array.length()) {
                            // Get current json object
                            val aJSONElement = array.getJSONObject(i)

                            // Get the current title (json object) data
                            val movieTitles = aJSONElement.getString("movieTitle")
                            movieTitleArray[i] = movieTitles
                            spTitle.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, movieTitleArray)

                            val rank = aJSONElement.getString("IMDBRank")
                            movieRankArray[i] = rank
                            val year = aJSONElement.getString("movieYear")
                            movieYearArray[i] = year
                            val director = aJSONElement.getString("movieDirector")
                            movieDirectorArray[i] = director
                            val des = aJSONElement.getString("movieDesc")
                            movieDescArray[i] = des
                            val poster = aJSONElement.getString("moviePoster")
                            moviePosterArray[i] = poster
                            val stars = aJSONElement.getString("movieStars")
                            movieStarsArray[i] = stars
                            val youtubeCode = aJSONElement.getString("youtubeCode")
                            movieyoutubeCodeArray[i] = youtubeCode
                        }
                    }
                    catch (e: IOException){
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { error ->
                    Log.d(error.toString(), "There is some error")
                })
        requestQueue.add(request)

        //Spinner
        spTitle.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, i: Int, p3: Long) {
                tvInfo.text = ("Rank: " + movieRankArray[i] +
                        "\n" + "Year: " + movieYearArray[i] +
                        "\n" + "Director: "+ movieDirectorArray[i] +
                        "\n" + "Stars: " + movieStarsArray[i])
                tvDes.text = movieDescArray[i]

                //load image
                val imgURL = "http://www1.lasalle.edu/~blum/c349wks/IMDB/" + moviePosterArray[i]
                loadImageFromUrl(imgURL)
            }
        }

        //Button to show clip
        btnShow.setOnClickListener({
            val id = spTitle.selectedItemPosition
            //Toast.makeText(getApplicationContext(), ""+id, Toast.LENGTH_LONG).show();

            try {
                val ytc = movieyoutubeCodeArray[id]
                //Toast.makeText(getApplicationContext(), ytc, Toast.LENGTH_LONG).show();
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + ytc)))
            } catch (e: JSONException) {

            }
        })

    } // end of onCreate

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