package com.ievana.capygo_anmp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ievana.capygo_anmp.model.Game
import com.ievana.capygo_anmp.model.Member
import com.ievana.capygo_anmp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailMemberViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val detailMemberLD = MutableLiveData<List<Member>>()
    val imgLD = MutableLiveData<List<String>>()
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
            Log.d("DetailMemberViewModel", "Member: ${member}")
            loadingLD.postValue(false)
        }
    }

    fun fetchImg(idTeam : Int){
        detailMemberLoadErrorLD.value=false
        loadingLD.value=true
        launch {
            val db= buildDb(getApplication())
            val game = db.gameDao().getImageMember(idTeam)
            val imageList = game.map { it.image } // Ambil hanya properti `image` dari setiap Game
            imgLD.postValue(imageList as List<String>?) // Simpan daftar URL gambar ke LiveData
            Log.d("DetailMemberViewModel", "Gambar: $imageList")

            loadingLD.postValue(false)
        }
    }

}