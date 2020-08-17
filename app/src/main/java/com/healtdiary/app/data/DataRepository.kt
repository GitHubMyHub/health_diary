package com.healtdiary.app.data

import android.util.Log
import kotlinx.coroutines.*

class DataRepository(private val database: AppDatabase) {

    private val TAG = "PlaceRepository"

    suspend fun getData(): List<Data> {
        Log.d(TAG, "getData")

        return withContext(Dispatchers.IO) {

            return@withContext database.dataDao().getData()
        }

    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */

    fun insert(data: Data) {
        database.dataDao().insert(data)
    }

    fun update(data: Data) {
        database.dataDao().update(data)
    }

    /*fun insert(data: NewData) = viewModelScope.launch(Dispatchers.IO) {
        database.dataDao().insert(data)
    }*/

    fun deleteEntry(id: Int) {
        database.dataDao().deleteEntry(id)
    }

    fun deleteAll() {
        database.dataDao().deleteAll()
    }

    suspend fun clear() {
        Log.d(TAG, "clear")

        withContext(Dispatchers.IO) {

            //return@withContext database.dataDao().clear()
        }

    }

    suspend fun bufferPlaces(list: List<Data>) {
        Log.d(TAG, "bufferPlaces")
        withContext(Dispatchers.IO) {


            database.dataDao().insertAll(list)

            /*val test = database.terminDao().getPlaces()

            test.map {
                Log.d(TAG, it.appointmentDate)
            }*/
        }
    }
}