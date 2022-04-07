package com.csanad.goodtimes.reminders

import android.os.Parcelable
import android.text.Editable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Reminder(
    @SerializedName("repetition")
    var repetition:Int,
    @SerializedName("days")
    var days: MutableList<Int>,
    @SerializedName("description")
    var description: String,
    @SerializedName("from")
    var from: Long,
    @SerializedName("time")
    var time: Long
)