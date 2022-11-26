package com.example.holkonnyitsek.data

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DataManagerInterface {

    lateinit var WCList: MutableList<WCObject>

    lateinit var SelectedWC: WCObject

    fun init() {
        WCList = mutableListOf<WCObject>(WCObject(192.23f,
            231.23f,
            "init",
            "init",
            mutableListOf<WCRating>(WCRating("init", "2022-10-10",5, "Szuper jo volt!!" ),),
            true,

            "ingyenes","id"
        ))
        println("before: " + WCList)
        getAllWC()
        println("after: " + WCList)
        if(WCList.size == 0){
            SelectedWC = WCObject(192.23f,
                231.23f,
                "init",
                "init",
                mutableListOf<WCRating>(WCRating("init", "2022-10-10",5, "Szuper jo volt!!" ),),
                true,

                "ingyenes","id"
            )
        }
        else SelectedWC = WCList.get(0)
    }

    fun getAllWC() = runBlocking{
        // get wc/get/all
        // for all in response[] unwrapWCObject
        val toiletService = RetrofitHelper.getInstance().create(ToiletService::class.java)
        // launching a new coroutine
        val asyncResult = async {
            val result = toiletService.getToilets()
            if (result != null) {
                // Checking the results
                Log.d("ayush: ", result.body().toString())
            }
            println("before result body" + result.body())
            if(result.body() != null){
                WCList = result.body()!!
            }
        }
        val result = asyncResult.await()
    }

    fun delWC(WC: WCObject) = runBlocking {
        // wc/del/id
        val toiletService = RetrofitHelper.getInstance().create(ToiletService::class.java)
        // launching a new coroutine
        val asyncResult = async {
            val result = toiletService.deleteToilet(WC.id)
            if (result != null) {
                // Checking the results
                Log.d("ayush: ", result.body().toString())
            }
        }
        val result = asyncResult.await()
        getAllWC()

    }

    fun addWC(WC: WCObject) = runBlocking {
        // wc/add


        val toiletService = RetrofitHelper.getInstance().create(ToiletService::class.java)
        // launching a new coroutine
        val asyncResult = async {
            val result = toiletService.addToilet(WC)
            if (result != null) {
                // Checking the results
                Log.d("ayush: ", result.body().toString())
            }
        }
        val result = asyncResult.await()
        getAllWC()

    }

    fun editWC() {
        // wc/edit/id
    }

    fun rateWC() {
        // wc/rate/id
    }

    fun wrapWCObject(): String {
        // WCObject to JSON
        return Gson().toJson(WCList)

    }

    fun unwrapWCObject(dataFromServer: String) {
        // JSON to WCObject
        val gson = Gson()
        val itemType = object : TypeToken<MutableList<WCObject>>() {}.type
        var tempWCList: MutableList<WCObject> = gson.fromJson<MutableList<WCObject>>(dataFromServer, itemType)
    }


}