package com.example.beautyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast

class DesignerActivity : AppCompatActivity() {
    lateinit var btn: Button
    lateinit var rb:RatingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_designer)

        btn= findViewById<View>(R.id.btnRate) as Button
        rb= findViewById<View>(R.id.ratingBar) as RatingBar


        btn.setOnClickListener{
            val ratingvalue = rb.rating
            Toast.makeText(this, "Rating is: $ratingvalue", Toast.LENGTH_LONG).show()
        }
    }

}