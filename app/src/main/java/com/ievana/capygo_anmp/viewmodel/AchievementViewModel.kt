package com.ievana.capygo_anmp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ievana.capygo_anmp.model.Achievement
import com.ievana.capygo_anmp.model.Game
import com.ievana.capygo_anmp.model.Team
import com.ievana.capygo_anmp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.Year
import kotlin.coroutines.CoroutineContext

class AchievementViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val achievesLD = MutableLiveData<List<Achievement>>()
    val imgLD = MutableLiveData<List<String>>()
    val achievesLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val job = Job()

    fun refresh(idGame:Int){
        loadingLD.value = true
        achievesLoadErrorLD.value = false
        launch {
            val db = buildDb(getApplication())
            val ach = db.gameDao().getAch(idGame)
            achievesLD.postValue(ach)
            Log.d("AchViewModel", "Ach:${ach}")
            loadingLD.postValue(false)
        }
    }

    fun refreshSelectYear(idGame:Int, selectedYear:String){
        loadingLD.value = true
        achievesLoadErrorLD.value = false
        launch {
            val db = buildDb(getApplication())
            val ach = db.gameDao().getAchYear(idGame,selectedYear)
            achievesLD.postValue(ach)
            Log.d("AchViewModel", "Ach Selected Year:${ach}")
            loadingLD.postValue(false)
        }
    }

    fun fetchImg(idGame : Int){
        achievesLoadErrorLD.value=false
        loadingLD.value=true
        launch {
            val db= buildDb(getApplication())
            val game = db.gameDao().getImage(idGame)
            val imageList = game.map { it.image } // Ambil hanya properti `image` dari setiap Game
            imgLD.postValue(imageList as List<String>?) // Simpan daftar URL gambar ke LiveData
            Log.d("imgach", "Gambar: $imageList")

            loadingLD.postValue(false)
        }
    }
//    val TAG = "volleyTag"
//    private var queue: RequestQueue? = null
//    fun refresh(gameName: String, yearAch: String) {
//        loadingLD.value = true
//        achievesLoadErrorLD.value = false
//
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://www.jsonkeeper.com/b/OQ04" // URL JSON
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url, { response ->
//                val sType = object : TypeToken<List<Game>>() {}.type
//                val result = Gson().fromJson<List<Game>>(response, sType)
//
//                // Mencari game berdasarkan nama
//                val game = result.find { it.name == gameName }
//
//                // Jika game ditemukan, ambil daftar achievements
//                if (game != null) {
//                    if(yearAch != ""){
//
//                            val filteredAchievements = game.achievements.filter { it.year.toString() == yearAch }
//                            if(filteredAchievements!=null) {
//                                achievesLD.value = ArrayList(filteredAchievements)
//                                Log.e("hasil", "$filteredAchievements")
//                            }
//                    }
//                    else {
//                        achievesLD.value = ArrayList(game.achievements)
//                    }
//                    // Set filtered achievements
//                } else {
//                    achievesLD.value = arrayListOf() // Jika game tidak ditemukan
//
//                }
//
//                loadingLD.value = false
//            }, { error ->
//                Log.d("Error", error.toString())
//                achievesLoadErrorLD.value = true
//                loadingLD.value = false
//            }
//        )
//
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
//    }
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}