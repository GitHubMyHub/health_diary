package com.healtdiary.app.ui.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.healtdiary.app.data.AppDatabase.Companion.getDatabase
import com.healtdiary.app.data.Data
import com.healtdiary.app.data.DataRepository
import com.healtdiary.app.data.NewData
import kotlinx.coroutines.*

class DataViewModel(application: Application) : AndroidViewModel(application) {

    val TAG = "DataViewModel"

    enum class DataApiStatus { LOADING, ERROR, DONE }

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<DataApiStatus>()

    // The external immutable LiveData for the request status String
    val status: LiveData<DataApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<Data>>()

    val properties: LiveData<List<Data>>
        get() = _properties

    private val _newData = MutableLiveData<NewData>()

    val newData: LiveData<NewData>
        get() = _newData

    private val _editStatus = MutableLiveData<Boolean>().apply { value = false }

    val editStatus: LiveData<Boolean>
        get() = _editStatus

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    /**
     * The data source this ViewModel will fetch results from.
     */
    private var dataRepository: DataRepository? = null

    init {
        Log.d(TAG, "INIT")

        //_editStatus.value = false

        coroutineScope.launch {
            dataRepository = DataRepository(getDatabase(application))
            _properties.value = dataRepository!!.getData()
        }

    }

    fun insert() {
        coroutineScope.launch(Dispatchers.IO) {

            dataRepository!!.insert(newData.value!!.toData())
        }
    }

    fun update() {
        coroutineScope.launch(Dispatchers.IO) {

            dataRepository!!.update(newData.value!!.toData())
        }
    }

    fun deleteEntry(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {

            dataRepository!!.deleteEntry(id)
        }
    }

    fun setData(place: String, value: String) {
        Log.d(TAG, "setData")
        Log.d(TAG, place)
        Log.d(TAG, value)

        val newItem = NewData(_newData.value)

        if (place == "editTextId") {
            newItem.mId = value
        }else if (place == "radioButtonMale") {
            newItem.mGender = value
        } else if (place == "radioButtonFemale") {
            newItem.mGender = value
        } else if (place == "editTextHeight") {
            newItem.mHeight = value
        } else if (place == "editTextWeight") {
            newItem.mWeight = value
        } else if (place == "editTextSystol") {
            newItem.mBloodSystol = value
        } else if (place == "editTextDiastol") {
            newItem.mBloodDiastol = value
        } else if (place == "editTextPulse") {
            newItem.mPulse = value
        } else if (place == "editTextBloodSugar") {
            newItem.mBloodSugar = value
        }

        //Log.d("OVAL", newData.height.toString())

        _newData.value = newItem

        Log.d("mId", _newData.value?.mId.toString())
        Log.d("mDateTime", _newData.value?.mDateTime.toString())
        Log.d("mGender", _newData.value?.mGender.toString())
        Log.d("mHeight", _newData.value?.mHeight.toString())
        Log.d("mWeight", _newData.value?.mWeight.toString())
        Log.d("mBloodSystol", _newData.value?.mBloodSystol.toString())
        Log.d("mBloodDiastol", _newData.value?.mBloodDiastol.toString())
        Log.d("mPulse", _newData.value?.mPulse.toString())
        Log.d("mBloodSugar", _newData.value?.mBloodSugar.toString())

    }

    fun getData() {
        coroutineScope.launch {
            _properties.value = dataRepository!!.getData()
        }
    }

    fun setEditMode() {

        val mode = !_editStatus.value!!

        Log.d("EDITMODE:", mode.toString())
        _editStatus.value = !_editStatus.value!!
    }

    fun updateEntries() {
        coroutineScope.launch {
            _properties.value = dataRepository!!.getData()
        }
    }

}