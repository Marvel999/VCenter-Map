package com.WrapX.vcentremap

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import es.voghdev.pdfviewpager.library.RemotePDFViewPager
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter
import es.voghdev.pdfviewpager.library.remote.DownloadFile
import es.voghdev.pdfviewpager.library.util.FileUtil


class PdfViewer : AppCompatActivity() , DownloadFile.Listener{
    private lateinit var adapter:PDFPagerAdapter
   private lateinit var root: LinearLayout
   private lateinit var loading: ProgressBar
   private lateinit var remotePDFViewPager: RemotePDFViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_viewer)

        val url = "https://github.com/Marvel999/Imgur-App/files/6554245/Goa.pdf"
        root=findViewById(R.id.pdfViewer)
        loading=findViewById(R.id.loading)
//         remotePDFViewPager = RemotePDFViewPager(this, url, this)

        setDownloadButtonListener(url)

    }

    override fun onSuccess(url: String?, destinationPath: String?) {
        loading.visibility=View.GONE;
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
        loading.visibility=View.VISIBLE;
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
            remotePDFViewPager,
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        if (adapter != null) {
            adapter.close()
        }
    }


}