package com.example.holkonnyitsek.data

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

            "ingyenes",
        ))
        println("before: " + WCList)
        getAllWC()
        println("after: " + WCList)

        SelectedWC = WCList.get(0)
    }

    fun getAllWC() {
        // get wc/get/all
        // for all in response[] unwrapWCObject
        val toiletService = RetrofitHelper.getInstance().create(ToiletService::class.java)
        // launching a new coroutine
        GlobalScope.launch {
            val result = toiletService.getToilets()
            if (result != null) {
                // Checking the results
                Log.d("ayush: ", result.body().toString())
            }
            WCList = result.body()!!
        }
    }

    fun delWC() {
        // wc/del/id
    }

    fun addWC(WC: WCObject) {
        // wc/add


        val toiletService = RetrofitHelper.getInstance().create(ToiletService::class.java)
        // launching a new coroutine
        GlobalScope.launch {
            val result = toiletService.addToilet(WC)
            println("resulttt" + WC)
            if (result != null) {
                // Checking the results
                Log.d("ayush: ", result.body().toString())
            }
        }
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