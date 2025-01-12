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
import com.ievana.capygo_anmp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class ListViewModel(application: Application):AndroidViewModel(application), CoroutineScope{
    val gamesLD = MutableLiveData<List<Game>>()
    val gameLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO


//    fun refresh() {
//        loadingLD.value = true
//        gameLoadErrorLD.value = false
//        launch {
//            val db = buildDb(
//                getApplication()
//            )
//            gamesLD.postValue(db.gameDao().selectGame())
//            loadingLD.postValue(false)
//        }
//    }

    fun refresh() {
        loadingLD.value = true
        gameLoadErrorLD.value = false
        launch {
            // Ambil instance database dari singleton
            val db = buildDb(getApplication())

            // Ambil data game dari database
            val games = db.gameDao().selectGame()
            gamesLD.postValue(games)
            loadingLD.postValue(false)
        }
    }

    fun clearTask(game: Game){
        launch {
            val db = buildDb(getApplication())
//            db.capygoDao().deleteTodo(todo)
//            todoLD.postValue((db.todoDao().selectAllTodo()))
        }
    }
}