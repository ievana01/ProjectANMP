package com.ievana.capygo_anmp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ievana.capygo_anmp.model.PropDatabase
import com.ievana.capygo_anmp.model.Proposal

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProposalViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope {
    val proposalLD = MutableLiveData<List<Proposal>>()
    val proposalLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh() {
        loadingLD.value = true
        proposalLoadErrorLD.value = false
        launch {
            val db = PropDatabase.buildDatabase(
                getApplication()
            )
            proposalLD.postValue(db.propDao().selectAllProp())

            loadingLD.postValue(false)
        }
    }


    fun addProp(list:List<Proposal>) {
        launch {
            val db = PropDatabase.buildDatabase(
                getApplication()
            )
            db.propDao().insertAll(*list.toTypedArray())
        }
    }




}

