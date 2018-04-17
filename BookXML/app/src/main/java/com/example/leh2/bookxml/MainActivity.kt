package com.example.leh2.bookxml

import android.content.res.XmlResourceParser
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.*
import com.squareup.picasso.Picasso
import org.xmlpull.v1.XmlPullParserException

class MainActivity : AppCompatActivity() {

    var myList = arrayListOf<Book>()
    private lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tVAuthor = findViewById<TextView>(R.id.tVAuthor)
        img = findViewById(R.id.imgV)
        val tvGenre = findViewById<TextView>(R.id.tvGenre)

        try{
            val bookList = ArrayList<String?>()

            val parser = resources.getXml(R.xml.book)
            var eventType = -1
            var myIndex = -1
//
            while(eventType != XmlResourceParser.END_DOCUMENT){
                if(eventType == XmlResourceParser.START_TAG){
                    val tagName = parser.name
                    if (tagName == "element") {
                        myList.add(Book())
                        myIndex++
//                        Toast.makeText(baseContext, myList[myIndex].title, Toast.LENGTH_LONG).show()
                    }
                    else{
                        parser.next() //move to text
                        Toast.makeText(baseContext, parser.name, Toast.LENGTH_LONG).show()
                        try {
                            val f1 = (myList[myIndex]).javaClass.getField(tagName)
                            f1.set(myList[myIndex], parser.text)
                            parser.next()   //move to end tag
                        }catch(e: Exception){
                            // tags like root and horses end up here -- not field names
                        }
                    } // end of else statement

                }//end of if (eventType == XmlResourceParser.START_TAG)
                eventType = parser.next()
            }// end of while
//
//            Toast.makeText(baseContext, myList.size.toString(), Toast.LENGTH_SHORT).show()
            for (item in myList) {
//                item.title =
                bookList.add(item.title)

            }
//
            val spTitle = findViewById<Spinner>(R.id.spTitle)
            spTitle.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, bookList)

            spTitle.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {}

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    tVAuthor.text = myList[p2].author
                    tvGenre.text = myList[p2].genre

                    val imgURL = myList[p2].cover
                    loadImageFromUrl(imgURL)
                }
            }
//
        }// end of try
        catch (e: Exception){
            Toast.makeText(baseContext, "Problem with file", Toast.LENGTH_LONG).show();
            Log.d("MyApp", e.toString())
        }
        catch(e: XmlPullParserException){
            Toast.makeText(baseContext, "Problem XML parsing problem", Toast.LENGTH_LONG).show()
            Log.d("MyApp", e.toString())
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
