package com.ievana.capygo_anmp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.ievana.capygo_anmp.model.Game
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ievana.capygo_anmp.model.CapyGoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class ListViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val gamesLD = MutableLiveData<ArrayList<Game>>()
    val gameLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    private var job = Job()
    fun refresh(){
        loadingLD.value = true
        gameLoadErrorLD.value = false

//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://www.jsonkeeper.com/b/HU7W"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                //success
//                val sType = object :TypeToken<List<Game>>(){ }.type
//                val result = Gson().fromJson<List<Game>>(it, sType)
//                gamesLD.value = result as ArrayList<Game>?
//                loadingLD.value = false
//                Log.d("showvoley",result.toString())
//            },{
//                Log.d("showvoley", it.toString())
//                gameLoadErrorLD.value = false
//                loadingLD.value = false
//            }
//        )
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
        launch {
            val db = CapyGoDatabase.buildDatabase(
                getApplication()
            )
            gamesLD.postValue(ArrayList(db.capygoDao().selectGame()))
            loadingLD.postValue(false)
        }
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}