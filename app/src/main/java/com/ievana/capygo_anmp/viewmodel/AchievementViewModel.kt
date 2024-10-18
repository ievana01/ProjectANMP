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

class AchievementViewModel(application: Application):AndroidViewModel(application) {
    val achievesLD = MutableLiveData<ArrayList<Achievement>>()
    val achievesLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

//    fun refresh(){
//        loadingLD.value = true
//        achievesLoadErrorLD.value = false
//
//        queue = Volley.newRequestQueue(getApplication())
//        var url = "https://www.jsonkeeper.com/b/IRFG"
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,{
//                val sType = object : TypeToken<List<Achievement>>(){}.type
//                val result = Gson().fromJson<List<Achievement>>(it, sType)
//                achievesLD.value = result as ArrayList<Achievement>?
//                loadingLD.value = false
//                Log.d("achievevoley",result.toString())
//            },{
//                Log.d("achievevoley", it.toString())
//                achievesLoadErrorLD.value = false
//                loadingLD.value = false
//            }
//        )
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
//    }
    fun refresh(gameName: String) {
        loadingLD.value = true
        achievesLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.jsonkeeper.com/b/HU7W"
        val stringRequest = StringRequest(
            Request.Method.GET, url, { response ->
                val sType = object : TypeToken<List<Game>>() {}.type
                val result = Gson().fromJson<List<Game>>(response, sType)

                // Ambil pencapaian dari game yang sesuai dengan gameName
                val gameAchievements = result.find { it.name.equals(gameName, ignoreCase = true) }?.achievements ?: emptyList()

                achievesLD.value = ArrayList(gameAchievements) // Update LiveData dengan pencapaian yang sesuai
                loadingLD.value = false
                Log.d("achievevoley", gameAchievements.toString())
            },
            { error ->
                Log.d("achievevoley", error.toString())
                achievesLoadErrorLD.value = true
                loadingLD.value = false
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}