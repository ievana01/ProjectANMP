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
import java.time.Year

class AchievementViewModel(application: Application):AndroidViewModel(application) {
    val achievesLD = MutableLiveData<ArrayList<Achievement>>()
    val achievesLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    fun refresh(gameName: String, yearAch: String) {
        loadingLD.value = true
        achievesLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.jsonkeeper.com/b/OQ04" // URL JSON

        val stringRequest = StringRequest(
            Request.Method.GET, url, { response ->
                val sType = object : TypeToken<List<Game>>() {}.type
                val result = Gson().fromJson<List<Game>>(response, sType)

                // Mencari game berdasarkan nama
                val game = result.find { it.name == gameName }

                // Jika game ditemukan, ambil daftar achievements
                if (game != null) {
                    if(yearAch != ""){

                            val filteredAchievements = game.achievements.filter { it.year.toString() == yearAch }
                            if(filteredAchievements!=null) {
                                achievesLD.value = ArrayList(filteredAchievements)
                                Log.e("hasil", "$filteredAchievements")
                            }
                    }
                    else {
                        achievesLD.value = ArrayList(game.achievements)
                    }
                    // Set filtered achievements
                } else {
                    achievesLD.value = arrayListOf() // Jika game tidak ditemukan

                }

                loadingLD.value = false
            }, { error ->
                Log.d("Error", error.toString())
                achievesLoadErrorLD.value = true
                loadingLD.value = false
            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}