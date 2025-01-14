package com.ievana.capygo_anmp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ievana.capygo_anmp.model.Schedule
import com.ievana.capygo_anmp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
class ScheduleViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val scheduleLD = MutableLiveData<List<Schedule>>()
    val scheduleLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh() {
        loadingLD.value = true
        scheduleLoadErrorLD.value = false
        launch {
            try {
                val db = buildDb(getApplication())
                val schedules = db.gameDao().getAllSchedules()
                scheduleLD.postValue(schedules)
                Log.d("ScheduleViewModel", "Schedule $schedules")
            } catch (e: Exception) {
                scheduleLoadErrorLD.postValue(true)
                Log.e("ScheduleViewModel", "Error: ${e.message}")
            } finally {
                loadingLD.postValue(false)
            }
        }
    }

    fun clearTask(schedule: Schedule) {
        launch {
            try {
                val db = buildDb(getApplication())
//                db.gameDao().deleteSchedule(schedule)
                refresh() // Refresh data setelah menghapus
            } catch (e: Exception) {
                Log.e("ScheduleViewModel", "Error: ${e.message}")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel() // Batalkan coroutine jika ViewModel dihancurkan
    }
}
