package com.WrapX.vcentremap

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import es.voghdev.pdfviewpager.library.RemotePDFViewPager
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter
import es.voghdev.pdfviewpager.library.remote.DownloadFile
import es.voghdev.pdfviewpager.library.util.FileUtil


class PdfViewer : AppCompatActivity() , DownloadFile.Listener{
    private lateinit var adapter:PDFPagerAdapter
   private lateinit var root: LinearLayout
   private lateinit var toolbar: View
   private lateinit var backbtn: ImageView
   private lateinit var stateName: TextView
   private lateinit var remotePDFViewPager: RemotePDFViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_viewer)

        val url = getIntent().getStringExtra("link");

        root=findViewById(R.id.pdfViewer)
        toolbar=findViewById(R.id.toolbar)
        backbtn=findViewById(R.id.back_btn)
        stateName=findViewById(R.id.title)

        stateName.text=resources.getString(R.string.vaccination_centre)

        if (url != null) {
            setDownloadButtonListener(url)
        }

        backbtn.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onSuccess(url: String?, destinationPath: String?) {
        Log.e("succesfull",""+url)

        adapter = PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url))
        remotePDFViewPager.adapter = adapter
        updateLayout()

    }

    override fun onFailure(e: Exception?) {
        e!!.printStackTrace()
        Log.e("Fail to load",e.toString())
    }

    private fun setDownloadButtonListener(link:String){
        val ctx: Context = this
        val listener: DownloadFile.Listener = this
            remotePDFViewPager = RemotePDFViewPager(ctx,link,listener)
            remotePDFViewPager.id = R.id.pdfViewer
        updateLayout()

    }
    override fun onProgressUpdate(progress: Int, total: Int) {

        Log.e("Progress",""+progress+" : "+total)
    }

    fun updateLayout() {
        Log.e("udate Layout","done")

        root.removeAllViews()
        root.addView(
            toolbar,
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )
        root.addView(
            remotePDFViewPager,
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.close()
    }


}