package com.example.holkonnyitsek.data

data class WCObject(
    var longitude: Float,
    var latitude: Float,
    var name: String,
    var opening_hours: String,
    var ratings: MutableList<WCRating>,
    var isfree: Boolean,
    var Price: String,
    var _id: String
)
