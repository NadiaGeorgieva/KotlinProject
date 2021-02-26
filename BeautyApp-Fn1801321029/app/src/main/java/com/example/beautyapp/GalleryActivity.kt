package com.example.beautyapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : AppCompatActivity() {

    var modalList=ArrayList<Modal>()

    var names=arrayOf(
        "img1",
        "img2",
        "img3",
        "img4",
        "img5",
        "img6",
        "img7",
        "img8",
        "img9",
        "img10",
        "img11",
        "img12",
        "img13",
        "img14",
        "img15",
        "img16",
        "img17",
        "img18"
        )

    var images=intArrayOf(
        R.drawable.img1,
        R.drawable.img2,
        R.drawable.img3,
        R.drawable.img4,
        R.drawable.img5,
        R.drawable.img6,
        R.drawable.img7,
        R.drawable.img8,
        R.drawable.img9,
        R.drawable.img10,
        R.drawable.img11,
        R.drawable.img12,
        R.drawable.img13,
        R.drawable.img14,
        R.drawable.img15,
        R.drawable.img16,
        R.drawable.img17,
        R.drawable.img18)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)


        for(i in names.indices){
         modalList.add(Modal(names[i],images[i]))
        }

        var customAdapter=CustomAdapter(modalList,this)
        gridView.adapter=customAdapter
        gridView.setOnItemClickListener{adapterView,view,i,l ->
           var intent= Intent(this,ViewActivity::class.java)
            intent.putExtra("data",modalList[i])
            startActivity(intent)
        }
    }
     class CustomAdapter(
         var itemModel:ArrayList<Modal>,
         var context: Context
     ):BaseAdapter(){
         var layoutInflator=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


         override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {

             var view=view;
             if(view==null){
                 view=layoutInflator.inflate(R.layout.row_items,viewGroup,false)
             }

             var tvImageName=view?.findViewById<TextView>(R.id.imageName)
             var imageView=view?.findViewById<ImageView>(R.id.imageView)

             tvImageName?.text=itemModel[position].name
             imageView?.setImageResource(itemModel[position].image!!)

             return view!!
         }

         override fun getItem(position: Int): Any {
            return itemModel[position]
         }

         override fun getItemId(position: Int): Long {
           return position.toLong()
         }

         override fun getCount(): Int {
             return itemModel.size
         }

     }


}