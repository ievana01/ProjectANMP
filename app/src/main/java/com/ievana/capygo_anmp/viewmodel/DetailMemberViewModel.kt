package com.ievana.capygo_anmp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ievana.capygo_anmp.model.Member
import com.ievana.capygo_anmp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailMemberViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val detailMemberLD = MutableLiveData<List<Member>>()
    val detailMemberLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun fetchMember(id : Int){
        detailMemberLoadErrorLD.value=false
        loadingLD.value=true
        launch {
            val db= buildDb(getApplication())
            val member = db.gameDao().getMember(id)
            detailMemberLD.postValue(member)
            Log.d("DetailMemberViewModel", "Teams: ${member}")
            loadingLD.postValue(false)
        }
    }
}