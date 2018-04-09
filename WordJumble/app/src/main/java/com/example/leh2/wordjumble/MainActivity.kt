package com.example.leh2.wordjumble

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private val random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val point = TextView(this)

        val mutableWordList = mutableListOf<String>()
        val ins = resources.openRawResource(resources.getIdentifier("words", "raw", packageName))

        val inputAsString = ins.bufferedReader().use { it.readText() }
        val splitedString = inputAsString.split("\n")

        for (s in splitedString){
            if(!mutableWordList.contains(s)){
                mutableWordList.add(s.trim())
            }
        }

        var pts = 0

        val tlBoard = findViewById<TableLayout>(R.id.tlBoard)

        val tr0 = TableRow(this)
        val tVJumbled = TextView(this)
        tr0.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)
        tlBoard.addView(tr0, TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT))
        tr0.addView(tVJumbled)

        val tr1 = TableRow(this)
        val tvAnswer = EditText(this)
        tr1.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)
        tlBoard.addView(tr1, TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT))
        tr1.addView(tvAnswer)

        var word = ""

        val btn = Button(this)
        btn.text = "NEXT ROUND"
        btn.setOnClickListener({
            tvAnswer.setBackgroundResource(R.color.white)
            tvAnswer.setText("")
            // get word
            val index = rand(0, mutableWordList.size)
            word = mutableWordList[index]
            val jumbledWord = jumble(word)

            tVJumbled.text = jumbledWord
        })
        val tr2 = TableRow(this)
        tr2.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)
        tlBoard.addView(tr2, TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT))
        tr2.addView(btn)

        val btnCheck = Button(this)
        btnCheck.text = "CHECK ANSWER"
        btnCheck.setOnClickListener({

            if(tvAnswer.text.toString() == word){
                pts++
                Toast.makeText(baseContext, "Correct", Toast.LENGTH_SHORT).show()
                tvAnswer.setBackgroundResource(R.color.green)
                point.text = pts.toString()
            } else {
                tvAnswer.setBackgroundResource(R.color.red)
                Toast.makeText(baseContext, "Try Again", Toast.LENGTH_SHORT).show()
            }
        })

        val tr3 = TableRow(this)
        tr3.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)
        tlBoard.addView(tr3, TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT))
        tr3.addView(btnCheck)

        val tr4 = TableRow(this)
        tr4.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)
        tlBoard.addView(tr4, TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT))
        tr4.addView(point)

    }

    // adapt from https://stackoverflow.com/questions/20588736/how-can-i-shuffle-the-letters-of-a-word
    private fun jumble(word: String): String {
        val wordChar = word.toCharArray()
        for (i in 0 until wordChar.size) {
            val j = random.nextInt(wordChar.size)
            // Swap letters
            val temp = wordChar[i]
            wordChar[i] = wordChar[j]
            wordChar[j] = temp
        }
        return String(wordChar)
    }

    // adapt from https://stackoverflow.com/questions/45685026/how-can-i-get-a-random-number-in-kotlin
    private fun rand(from: Int, to: Int): Int{
        return random.nextInt(to - from) + from
    }
}
