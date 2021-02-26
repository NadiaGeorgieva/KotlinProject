package com.example.beautyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var btnStartNote=findViewById<Button>(R.id.buttonStartNote)

        btnStartNote.setOnClickListener{
            var msg="msg"
            var crtIntent= Intent(this,NoteActivity::class.java).apply {
                putExtra(msg,msg)
            }
            startActivity(crtIntent)
        }

    }
}