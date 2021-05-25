package com.WrapX.vcentremap.ui.FindVCentre

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.WrapX.vcentremap.repo.firebaseDatabase.GetVCentre
import com.WrapX.vcentremap.repo.model.VCentre
import kotlinx.coroutines.launch

class FindVCentreViewModel : ViewModel() {
    private var getdata= GetVCentre();
    private var _vCList=MutableLiveData<ArrayList<VCentre>>();
     var vCList:LiveData<ArrayList<VCentre>> = _vCList;

    fun getData(){

        val list=getdata.getdata();
        Log.e("List",""+list)
       vCList =getdata._mlist


    }


}