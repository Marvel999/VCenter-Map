package com.WrapX.vcentremap.repo.firebaseDatabase

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.WrapX.vcentremap.repo.model.VCentre
import com.google.firebase.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class GetVCentre {

    suspend fun getdata(mlist: (list: ArrayList<VCentre>) -> Unit) = withContext(Dispatchers.IO) {
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var myRef: DatabaseReference = database.getReference()
        val dataSnapshot = myRef.get().await()
        val list = ArrayList<VCentre>()
        for (snap in dataSnapshot.children) {
            var count = 0
            val centreName = snap.child("VCName").value.toString()
            val vcAddress = snap.child("VCAddress").value.toString()
            val vcPinCode = snap.child("PinCode").value.toString()
            val vCGoogleMap = snap.child("GoogleMap").value.toString()
            Log.e("Name", centreName);
            val vCentre = VCentre(centreName, vcAddress, vcPinCode, vCGoogleMap)
            list.add(vCentre)
        }
        mlist(list)
    }

}