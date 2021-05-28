package com.WrapX.vcentremap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView

class InfoActivity : AppCompatActivity() {

    private lateinit var backBtn:ImageView
    private lateinit var title:TextView
    private lateinit var telegram:TextView
    private lateinit var website:TextView
    private lateinit var paytmFinder:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        backBtn=findViewById(R.id.back_btn)
        title=findViewById(R.id.title)
        website=findViewById(R.id.website)
        paytmFinder=findViewById(R.id.ptmfinder)
        telegram=findViewById(R.id.telegramGroup)
        title.text="Tips & Tricks"
        setClickableText()
        backBtn.setOnClickListener {
            onBackPressed()
        }



    }

    fun setClickableText(){
        website.setMovementMethod(LinkMovementMethod.getInstance());
        paytmFinder.setMovementMethod(LinkMovementMethod.getInstance());
        telegram.setMovementMethod(LinkMovementMethod.getInstance());

    }
}