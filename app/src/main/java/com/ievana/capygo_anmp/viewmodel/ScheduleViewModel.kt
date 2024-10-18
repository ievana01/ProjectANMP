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
import com.ievana.capygo_anmp.model.Schedule

class ScheduleViewModel (application: Application):AndroidViewModel(application){
    val scheduleLD = MutableLiveData<ArrayList<Schedule>>()
    val scheduleLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private  var queue: RequestQueue? = null

    fun refresh(){
        loadingLD.value = true
        scheduleLoadErrorLD.value = false

        queue =  Volley.newRequestQueue(getApplication())
        val url = "https://www.jsonkeeper.com/b/1UOH"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object :TypeToken<List<Schedule>>(){ }.type
                val result = Gson().fromJson<List<Schedule>>(it,sType)
                scheduleLD.value = result as ArrayList<Schedule>?
                loadingLD.value = false
                Log.d("showvoley",result.toString())
            },{
                Log.d("showvoley",it.toString())
                scheduleLoadErrorLD.value = false
                loadingLD.value = false
            }

        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}