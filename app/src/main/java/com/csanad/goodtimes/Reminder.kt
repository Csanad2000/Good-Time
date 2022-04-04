package com.csanad.goodtimes

import com.google.gson.annotations.SerializedName

data class Reminder(
    @SerializedName("repetition")
    var repetition:Int,
    @SerializedName("days")
    var days:ArrayList<Int>?,
    @SerializedName("description")
    var description:String?,
    @SerializedName("from")
    var from:Long,
    @SerializedName("time")
    var time:Long
)