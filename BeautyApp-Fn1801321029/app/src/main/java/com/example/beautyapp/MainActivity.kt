package com.example.beautyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var houseImage= findViewById<ImageView>(R.id.note)
        var galleryImage=findViewById<ImageView>(R.id.gallery)
        var designerImage=findViewById<ImageView>(R.id.designer)


        houseImage.setOnClickListener{
            var msg="msg"
            var noteIntent= Intent(this,HomeActivity::class.java).apply {
                putExtra(msg,msg)
            }
            startActivity(noteIntent)
        }

        galleryImage.setOnClickListener{
            var msg="msg"
            var galleryIntent= Intent(this,GalleryActivity::class.java).apply {
                putExtra(msg,msg)
            }
            startActivity(galleryIntent)
        }

        designerImage.setOnClickListener{
            var msg="msg"
            var designerIntent= Intent(this,DesignerActivity::class.java).apply {
                putExtra(msg,msg)
            }
            startActivity(designerIntent)

        }
        
    }
}