package com.example.leh2.book

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //get array from string.xml
        val title = resources.getStringArray(R.array.title)
        val author = resources.getStringArray(R.array.author)
        val genre = resources.getStringArray(R.array.genre)

        //get item from activity_main.xml
        val imgVtitle = findViewById<ImageView>(R.id.imgView)
        val tvAuthor = findViewById<TextView>(R.id.tVAuthor)
        val tVGenre = findViewById<TextView>(R.id.tvGenre)

        //spinner
        var spCover = findViewById<Spinner>(R.id.spTitle)
        spCover.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.title))

        //spinner onChange
        spCover.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {//fix "object is not abstract" https://www.youtube.com/watch?v=D5l7MNlqA3M
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val drawableResourceName = title[p2].toString().toLowerCase().replace(' ', '_')
                tvAuthor.text = author[p2]
                tVGenre.text = genre[p2]
                val id = imgVtitle.context.resources.getIdentifier(drawableResourceName, "drawable", imgVtitle.context.packageName)
                imgVtitle.setImageResource(id)
            }
        }
    }
}