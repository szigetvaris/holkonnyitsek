package com.example.holkonnyitsek.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataManagerInterface {

    lateinit var WCList: MutableList<WCObject>

    lateinit var SelectedWC: WCObject

    fun init() {
        WCList = mutableListOf<WCObject>(WCObject(192.23f,
            231.23f,
            "BME Q epulet",
            "07:00-20:00",
            mutableListOf<WCRating>(WCRating("Bela", "2022.08.33",5, "Szuper jo volt!!" ),
                WCRating("Juci", "2022.08.33", 3, "Voltam mar jobban is.."),
                WCRating("Juci", "2022.08.34", 5, "Masodjara sokkal jobban tetszett :)"),
                WCRating("Bela", "2022.08.35",2, "Mindig szeretek ide szarni!" ),
                WCRating("Sanya", "2022.08.33", 2, "Lorem ipsum subi dubi legyen ez a komment tuti$$ szerintem lehetne jobb is rosszabb is most 2 csillagot adtam ra es csak jartatom a szam")
            ),
            true,
            "Ingyenes",
        ))
        SelectedWC = WCList.get(0)
    }

    fun getAllWC() {
        // get wc/get/all
        // for all in response[] unwrapWCObject
    }

    fun delWC() {
        // wc/del/id
    }

    fun addWC() {
        // wc/add
    }

    fun editWC() {
        // wc/edit/id
    }

    fun rateWC() {
        // wc/rate/id
    }

    fun wrapWCObject() {
        // WCObject to JSON
        Gson().toJson(WCList)

    }

    fun unwrapWCObject(dataFromServer: String) {
        // JSON to WCObject
        val gson = Gson()
        val itemType = object : TypeToken<MutableList<WCObject>>() {}.type
        var tempWCList: MutableList<WCObject> = gson.fromJson<MutableList<WCObject>>(dataFromServer, itemType)
    }
}