package com.WrapX.vcentremap.ui.FindSlot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.WrapX.vcentremap.repo.model.Session
import com.WrapX.vcentremap.repo.retrofit.SlotFeed
import com.WrapX.vcentremap.repo.retrofit.SlotRepo
import kotlinx.coroutines.launch

class FindSlotViewModel : ViewModel() {

    var repo: SlotFeed = SlotRepo()
    private var _vCList = MutableLiveData<List<Session>>();
    var vCList: LiveData<List<Session>> = _vCList;

    fun getFeed(pincode: String, data: String) {
        viewModelScope.launch {
            repo.getFeed(pincode, data).let {
                _vCList.postValue(it.body()?.sessions)
            }
        }
    }
}