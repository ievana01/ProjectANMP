package com.ievana.capygo_anmp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ievana.capygo_anmp.model.CapyGoDatabase
import com.ievana.capygo_anmp.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


    class UserViewModel(application: Application)
        : AndroidViewModel(application), CoroutineScope {
        val todoLD = MutableLiveData<List<User>>()
        val todoLoadErrorLD = MutableLiveData<Boolean>()
        val loadingLD = MutableLiveData<Boolean>()
        private var job = Job()
        override val coroutineContext: CoroutineContext
            get() = job + Dispatchers.IO

        fun refresh() {
            loadingLD.value = true
            todoLoadErrorLD.value = false
            launch {
                val db = CapyGoDatabase.buildDatabase(
                    getApplication()
                )
                todoLD.postValue(db.capygoDao().selectAllTodo())
                loadingLD.postValue(false)
            }
        }


        fun addUser(list:List<User>) {
            launch {
                val db = CapyGoDatabase.buildDatabase(
                    getApplication()
                )
                db.capygoDao().insertAll(*list.toTypedArray())
            }
        }

        fun getUserByUsername(username: String): MutableLiveData<User?> {
            val userLD = MutableLiveData<User?>()
            launch {
                val db = CapyGoDatabase.buildDatabase(getApplication())
                val user = db.capygoDao().getUserByUsername(username)
                userLD.postValue(user)
            }
            return userLD
        }






    }


