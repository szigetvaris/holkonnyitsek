package com.example.holkonnyitsek.data

data class WCObject(
    val longitude: Float,
    val latitude: Float,
    val name: String,
    val opening_hours: String,
    val ratings: List<WCRating>,
    val isfree: Boolean,
    val Price: Float
)
