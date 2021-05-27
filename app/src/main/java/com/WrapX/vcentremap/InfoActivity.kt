package com.WrapX.vcentremap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class InfoActivity : AppCompatActivity() {

    private lateinit var backBtn:ImageView
    private lateinit var title:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        backBtn=findViewById(R.id.back_btn)
        title=findViewById(R.id.title)
        title.text="Tips & Tricks"
        backBtn.setOnClickListener {
            onBackPressed()
        }

    }
}