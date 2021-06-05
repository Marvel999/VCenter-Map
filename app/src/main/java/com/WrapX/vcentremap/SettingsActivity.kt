package com.WrapX.vcentremap

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.WrapX.vcentremap.adapter.CustomListAdapter
import com.WrapX.vcentremap.repo.SharePrefrance.UserSharedPreferences
import com.WrapX.vcentremap.repo.model.ListItem
import com.WrapX.vcentremap.ui.FindVCentre.FindVCentreViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SettingsActivity : AppCompatActivity(), CustomListAdapter.onItemClick {

    private lateinit var homeViewModel: FindVCentreViewModel

    private lateinit var listView:ListView
    private lateinit var backBtn:ImageView
    private lateinit var activityTitle:TextView
    private lateinit var stringArray:ArrayList<ListItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        listView=findViewById(R.id.listView)
        backBtn=findViewById(R.id.back_btn)
        activityTitle=findViewById(R.id.title)
        stringArray= ArrayList()
        stringArray.add(ListItem("Share our App with your friends",R.drawable.ic_share))
        stringArray.add(ListItem("Rating us on Google Play Store",R.drawable.ic_rating))
        stringArray.add(ListItem("Feedback & Support",R.drawable.ic_feedback))
        stringArray.add(ListItem("Logout",R.drawable.ic_logout))

        homeViewModel =
            ViewModelProvider(this).get(FindVCentreViewModel::class.java)

        val listAdapter= CustomListAdapter(this,this,stringArray)
        listView.adapter=listAdapter;

        activityTitle.text="Settings"

        backBtn.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onClick(name: String) {
        if(name==stringArray.get(0).name){
            shareApp()
        }
        else if(name==stringArray.get(1).name){
            rateApp()
        }
        else if(name==stringArray.get(2).name){
          supportAndFeedback()
        }
       else  if(name==stringArray.get(3).name){
           logoutApp()
       }
    }

    fun shareApp(){
        val intent = Intent(Intent.ACTION_SEND);
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_SUBJECT, "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID)
        intent.putExtra(Intent.EXTRA_TEXT, "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID)
        startActivity(Intent.createChooser(intent, "choose one"))
    }

    fun supportAndFeedback(){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "info.wrapx@gmail.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject")
        intent.putExtra(Intent.EXTRA_TEXT, "your_text")
        startActivity(intent)
    }

    fun logoutApp(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val mGoogleSignInClient= GoogleSignIn.getClient(this,gso)
        mGoogleSignInClient.signOut().addOnCompleteListener {
            val userSharedPreferences=UserSharedPreferences(this)
            userSharedPreferences.deleteSharePrefance()
            Firebase.auth.signOut()
            homeViewModel.deleteTable()
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }



    }

    fun rateApp(){
        val uri: Uri = Uri.parse("market://details?id=$packageName")
        val myAppLinkToMarket = Intent(Intent.ACTION_VIEW, uri)
        try {
            startActivity(myAppLinkToMarket)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show()
        }
    }


}