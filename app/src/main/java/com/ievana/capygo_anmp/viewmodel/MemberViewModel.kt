package com.ievana.capygo_anmp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ievana.capygo_anmp.model.Game
import com.ievana.capygo_anmp.model.GameDatabase
import com.ievana.capygo_anmp.model.Member
import com.ievana.capygo_anmp.model.Team
import com.ievana.capygo_anmp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class MemberViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    //untuk page who we are
    val imgLD = MutableLiveData<List<String>>()
    val membersLD = MutableLiveData<List<Team>>()
    val memberLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    //who we are
    fun fetchTeam(){
        memberLoadErrorLD.value = false
        loadingLD.value = true
        launch {
            val db = buildDb(getApplication())
            val teams = db.gameDao().selectTeam()
            membersLD.postValue(teams)
            Log.d("TeamViewModel", "Teams: ${teams}")
            loadingLD.postValue(false)
        }
    }



    private val dao = GameDatabase.invoke(application).gameDao()
    fun updateTeamLike(newLike:Int, idTeam:Int){
        viewModelScope.launch(Dispatchers.IO) {
            dao.updateLikeTeam(newLike, idTeam)
        }
    }

//    fun fetchMember(id : Int){
//        memberLoadErrorLD.value=false
//        loadingLD.value=true
//        launch {
//            val db= buildDb(getApplication())
//            val member = db.gameDao().getMember(id)
//            membersLD.postValue(member)
//            Log.d("DetailMemberViewModel", "Teams: ${member}")
//            loadingLD.postValue(false)
//        }
//    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh(idGame:Int) {
        memberLoadErrorLD.value = false
        loadingLD.value = true
        launch {
            val db = buildDb(getApplication())
            val member = db.gameDao().getTeam(idGame)
            membersLD.postValue(member)
            Log.d("MemberViewModel", "Teams: ${member}")
            loadingLD.postValue(false)
        }
    }
        fun fetchImg(idGame : Int){
            memberLoadErrorLD.value=false
            loadingLD.value=true
            launch {
                val db= buildDb(getApplication())
                val game = db.gameDao().getImage(idGame)
                val imageList = game.map { it.image } // Ambil hanya properti `image` dari setiap Game
                imgLD.postValue(imageList as List<String>?) // Simpan daftar URL gambar ke LiveData
                Log.d("MemberViewModel", "Gambar: $imageList")

                loadingLD.postValue(false)
            }
        }

}