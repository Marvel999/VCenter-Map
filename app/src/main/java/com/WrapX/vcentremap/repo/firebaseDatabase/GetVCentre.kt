package com.WrapX.vcentremap.repo.firebaseDatabase

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.WrapX.vcentremap.repo.model.VCentre
import com.google.firebase.database.*


class GetVCentre {
    private  var list=ArrayList<VCentre>()
     var _mlist=MutableLiveData<ArrayList<VCentre>>()
    var mlist: LiveData<ArrayList<VCentre>> = _mlist

    fun getdata(pinCode:String){
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var myRef: DatabaseReference = database.getReference()

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snap in dataSnapshot.children){
                    var count=0;
//                    Log.e("Data",""+snap)

                    val centreName=snap.child("VCName").value.toString()
                    val vcAddress=snap.child("VCAddress").value.toString()
                    val vcPinCode=snap.child("PinCode").value.toString()
                    val vCGoogleMap= snap.child("GoogleMap").value.toString()
                    Log.e("Name",centreName);
                    Log.e("Address",vcAddress);
                    Log.e("PinCode",vcPinCode);
                    Log.e("MapLink",vCGoogleMap);
                    val vCentre=VCentre(centreName,vcAddress,vcPinCode,vCGoogleMap)
                    if(pinCode.equals(vcPinCode))
                       list.add(vCentre)


                }
                _mlist.value=list
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
//                Toast.makeText(this@GetVCentre,"Sorry Not able to fact",Toast.LENGTH_LONG).show();
                Log.w("Fail", "Failed to read value.", error.toException())
            }
        })


    }

}